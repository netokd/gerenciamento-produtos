package com.gerenciamento.produtos;
//Lista de produtos e categorias

public class ProductManager {

    // Lista os detalhes de um produto.
    public static void detailsProduct(Product product) {
        System.out.println("\nNome: " + product.getName());
        System.out.println("Descrição: " + product.getDescription());
        System.out.println("Preço: " + product.getPrice());
        System.out.println("Quantidade:" + product.getQuantity());
        Category associatedCategory = product.getCategory();
        if (associatedCategory != null) {
            System.out.println("Detalhes da Categoria");
            System.out.println("Nome: " + associatedCategory.getName());
            System.out.println("");
        } else {
            System.out.println("\nCategoria não encontrada");
            System.out.println("");
        }
    }
}
