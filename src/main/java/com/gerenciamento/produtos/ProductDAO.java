package com.gerenciamento.produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class ProductDAO {
    private CategoryDAO categoryDAO;

    public ProductDAO() {
        this.categoryDAO = new CategoryDAO();
    }

    public void insertProduct(Product product) {
        String query = "INSERT INTO products (name, description,  price, quantity, category_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Preenche os valores dos parâmetros na query preparada
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setLong(5, product.getCategory().getId());

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
                // Criar um objeto Category e preencher com informações da categoria
                long categoryId = resultSet.getLong("category_id");
                Category category = categoryDAO.getCategoryById(categoryId); // Você precisaria criar um método para
                                                                             // buscar a
                // categoria pelo id

                product = new Product(name, description, price, quantity, category); // não passa o id pois não precisa
                product.setCategory(category); // Seta categoria associada
                product.setId(resultSet.getLong("id")); // usamos o setId pois parar criar e o BD cria

                // Imprima os valores para depuração
                System.out.println("Produto a ser alterado");
                ProductManager.detailsProduct(product);
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
        String query = "UPDATE products SET name = ?, description = ?, price = ?, quantity = ?, category_id = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setLong(5, product.getCategory().getId());
            preparedStatement.setLong(6, product.getId());

            preparedStatement.executeUpdate();

            System.out.println("Produto atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o produto: " + e.getMessage());
        }

    }

    public void removeProduct(long id) {
        String query = "DELETE FROM products WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Produto removido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao remover o produto: " + e.getMessage());
        }

    }

    // Cria um array e armazena todos os produtos nesse array.
    public Product[] listProducts() {
        String query = "SELECT * FROM products";
        List<Product> productList = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) { // prepara a query

            ResultSet resultSet = preparedStatement.executeQuery(); // executa a query

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                long categoryId = resultSet.getLong("category_id");

                Product product = new Product(name, description, price, quantity,
                        categoryDAO.getCategoryById(categoryId));
                productList.add(product);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar produtos: " + e.getMessage());
        }

        return productList.toArray(new Product[0]);
    }
}
