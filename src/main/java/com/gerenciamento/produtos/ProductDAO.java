package com.gerenciamento.produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO {
    public void insertProduct(Product product) {
        String query = "INSERT INTO products (name, description,  price, quantity) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Preenche os valores dos parâmetros na query preparada
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());

            // Executa a atualização no banco de dados (inserção)
            preparedStatement.executeUpdate();

            System.out.println("Produto inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir produto: " + e.getMessage());
        }
    }
}
