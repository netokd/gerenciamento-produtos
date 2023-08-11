package com.gerenciamento.produtos;

public class TestProductDAO {
    public static void main(String[] args) {
        // Cria uma instância de ProductDAO
        ProductDAO productDAO = new ProductDAO();

        // Cria um novo produto para inserir no banco de dados
        // Product product = new Product("Produto Teste 2", "Descrição do Produto 66",
        // 100.99, 10);

        // Buscar o produto pelo ID
        // long productIdToUpdate = 1; // id a ser localizado
        long productIdToDelete = 2; // id a ser deletado
        // Product productToUpdate = productDAO.findProductById(productIdToUpdate);

        productDAO.removeProduct(productIdToDelete);

        // if (productToUpdate != null) {
        // productToUpdate.setName("Atualizado");
        // productToUpdate.setDescription("Descrição do Produto foi atualizad");
        // productToUpdate.setPrice(55.55);
        // productToUpdate.setQuantity(0);

        // productDAO.updateProduct(productToUpdate);
        // } else {
        // System.out.println("Produto não encontrado");
        // }

        // Chama o método de inserção do ProductDAO
        // productDAO.insertProduct(product);

    }
}
