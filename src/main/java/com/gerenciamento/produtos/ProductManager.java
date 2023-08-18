package com.gerenciamento.produtos;
//Lista de produtos e categorias

import java.util.Scanner;

public class ProductManager {

    // Lista os detalhes de um produto.
    public static void detailsProduct(Product product, int haveId) {
        System.out.println("");
        if (haveId > 0) {
            System.out.println("ID: " + product.getId());
        }
        System.out.println("Nome: " + product.getName());
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

    public static void listProducts(String[] args, int haveId) {
        ProductDAO productDAO = new ProductDAO();

        for (Product product : productDAO.listProducts()) {
            detailsProduct(product, haveId);
        }
    }

    public static void updateProduct(String[] args, Scanner sc) {
        ProductDAO productDAO = new ProductDAO();
        System.out.println("Lista de Produtos com id:\n");
        listProducts(args, 1);
        System.out.println("digite o ID do produto:");
        int productId = sc.nextInt();
        sc.nextLine();
        Product productToUpdate = productDAO.findProductById(productId);
        System.out.println("Produto Selecionado: \n");
        detailsProduct(productToUpdate, 0);

        System.out.println("Digite o novo nome do produto");
        System.out.println("Ou Deixe vazio para não alterar:");
        String name = sc.nextLine();
        if (name == "") {
            name = productToUpdate.getName();
        }
        System.out.println("Digite a descrição do produto:");
        System.out.println("Ou Deixe vazio para não alterar:");
        String description = sc.nextLine();
        if (description == "") {
            description = productToUpdate.getDescription();
        }
        System.out.println("digite o valor do produto:");
        System.out.println("Ou digite um valor menor que 1 para não alterar:");
        double price = sc.nextDouble();
        if (price <= 0) {
            price = productToUpdate.getPrice();
        }
        System.out.println("digite a quantidade do produto:");
        System.out.println("Ou digite um valor menor que -10 para não alterar:");
        int quantity = sc.nextInt();
        if (quantity < -10) {
            quantity = productToUpdate.getQuantity();
        }
        productToUpdate = new Product(name, description, price, quantity, productToUpdate.getCategory());
        productToUpdate.setId(productId);

        productDAO.updateProduct(productToUpdate);

    }

    public static void removeProduct(String[] args, Scanner sc) {
    }
}
