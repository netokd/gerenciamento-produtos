package com.gerenciamento.produtos;
//Lista de produtos e categorias

import java.util.Scanner;

public class ProductManager {

    // Lista os detalhes de um produto.
    public static void detailsProduct(Product product, int haveId) {
        System.out.println("\nNome: " + product.getName());
        System.out.println("Descrição: " + product.getDescription());
        System.out.println("Preço: " + product.getPrice());
        System.out.println("Quantidade:" + product.getQuantity());
        Category associatedCategory = product.getCategory();

        if (haveId > 0) {
            System.out.println("ID: " + product.getId());
        }
        if (associatedCategory != null) {
            System.out.println("Detalhes da Categoria");
            System.out.println("Nome: " + associatedCategory.getName());
            System.out.println("");
        } else {
            System.out.println("\nCategoria não encontrada");
            System.out.println("");
        }
    }

    public static void listCategories(String[] args) {

        for (Category category : CategoryDAO.listCategories()) {
            System.out.println("ID: " + category.getId());
            System.out.println("Nome: " + category.getName());
            System.out.println("");
        }
    }

    public static void insertProduct(String[] args, Scanner sc) {
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        System.out.println("Lista de Categorias: \n");
        listCategories(args);
        System.out.println("Digite o ID da categoria:\n");
        int categoryId = sc.nextInt();
        sc.nextLine();

        Category category = categoryDAO.getCategoryById(categoryId);

        System.out.println("Digite o nome do produto:");
        String name = sc.nextLine();
        System.out.println("Digite a descrição do produto:");
        String description = sc.nextLine();
        System.out.println("digite o valor do produto:");
        double price = sc.nextDouble();
        System.out.println("digite a quantidade do produto:");
        int quantity = sc.nextInt();

        Product product = new Product(name, description, price, quantity, category);
        productDAO.insertProduct(product);

    }

    public static void listProducts(String[] args, Scanner sc) {
    }

    public static void updateProduct(String[] args, Scanner sc) {
    }

    public static void removeProduct(String[] args, Scanner sc) {
    }
}
