package com.gerenciamento.produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CategoryDAO {
    public void insertCategory(Category category) {
        String query = "INSERT INTO categories (name) VALUES (?)";

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Preenche os valores dos parâmetros na query preparada
            preparedStatement.setString(1, category.getName());

            // Executa a atualização no banco de dados (inserção)
            preparedStatement.executeUpdate();

            System.out.println("Categoria inserida com sucesso!");
        } catch (SQLException e) {

            System.err.println("Erro ao inserir categoria: " + e.getMessage());
        }
    }

    public void removeCategory(long id) {
        String query = "DELETE FROM categories WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Categoria removida com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao remover categoria: " + e.getMessage());
        }
    }

    public Category getCategoryById(long id) {

        String query = "SELECT * FROM categories ories WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Category thisCategory = new Category(name);
                thisCategory.setId(id);

                return thisCategory;

            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar categoria: " + e.getMessage());
        }
        return null;
    }

    public void updateCategory(Category category) {

        String query = "UPDATE categories SET name = ? WHERE id = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        System.out.println(category.getId());
        System.out.println(category.getName());

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, category.getName());

            preparedStatement.setLong(2, category.getId());

            preparedStatement.executeUpdate();

            System.out.println("Categoria atualizada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar categoria: " + e.getMessage());
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
    }
}
