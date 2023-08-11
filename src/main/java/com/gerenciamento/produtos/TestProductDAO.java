package com.gerenciamento.produtos;

public class TestProductDAO {
    public static void main(String[] args) {
        // Cria uma instância de ProductDAO
        ProductDAO productDAO = new ProductDAO();

        // Cria um novo produto para inserir no banco de dados
        Product product = new Product("Produto Teste", "Descrição do Produto", 19.99, 10);

        // Chama o método de inserção do ProductDAO
        productDAO.insertProduct(product);
    }
}
