package com.gerenciamento.produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

            Boolean validatedId = validId(id);

            if (validatedId == true) {

                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();

                System.out.println("Categoria removida com sucesso!");
            } else {
                System.out.println("Categoria não encontrada");
            }

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
        Boolean validatedId = validId(category.getId());
        System.out.println(category.getId());
        System.out.println(category.getName());

        if (validatedId == true) {
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
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("Categoria não encontrada");
        }
    }

    public Boolean validId(long id) {
        String query = "SELECT * FROM categories ories WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Boolean validatedId;

            if (resultSet.next()) {
                validatedId = true;

            } else {
                validatedId = false;
            }

            return validatedId;
        } catch (SQLException e) {
            System.err.println("Erro ao validar o ID: " + e.getMessage());
        }
        return null;

    }

    public static Category[] listCategories() {
        String query = "SELECT * FROM categories";
        List<Category> categoryList = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Category thisCategory = new Category(name);
                thisCategory.setId(resultSet.getLong("id"));
                categoryList.add(thisCategory);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar categorias: " + e.getMessage());
        }

        return categoryList.toArray(new Category[0]);
    }

}
