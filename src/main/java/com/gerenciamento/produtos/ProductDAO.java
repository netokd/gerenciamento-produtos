package com.gerenciamento.produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

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

    /**
     * @param id
     * @return
     */
    public Product findProductById(long id) { // procura se o produto pelo id
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Product product = null;

        String query = "SELECT * FROM products WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");

                // Imprima os valores para depuração
                System.out.println("Produto a ser alterado");
                System.out.println("Nome: " + name);
                System.out.println("Descrição: " + description);
                System.out.println("Preço: " + price);
                System.out.println("Quantidade: " + quantity);

                product = new Product(name, description, price, quantity); // não passa o id pois não precisa

                product.setId(resultSet.getLong("id")); // usamos o setId pois parar criar e o BD cria
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o produto: " + e.getMessage());
        } finally {
            // Feche o resultSet e o statement se estiverem abertos
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return product;

    }

    // Função para atualizar um produto
    public void updateProduct(Product product) { // atualiza o banco de dados pelo id
        String query = "UPDATE products SET name = ?, description = ?, price = ?, quantity = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            System.out.println("Produto a ser atualizado dentro do update:");
            System.out.println("Nome: " + product.getName());
            System.out.println("Id: " + product.getId());
            System.out.println("Descrição: " + product.getDescription());
            System.out.println("Preço: " + product.getPrice());
            System.out.println("Quantidade: " + product.getQuantity());

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());

            preparedStatement.setLong(5, product.getId());
            preparedStatement.executeUpdate();

            System.out.println("Produto atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o produto: " + e.getMessage());
        }

    }
}
