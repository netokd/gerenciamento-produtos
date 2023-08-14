package com.gerenciamento.produtos;

public class TestCategoryDAO {

    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();
        // Category category = new Category("Bebidas");
        // categoryDAO.insertCategory(category);

        // categoryDAO.removeCategory(1);
        // try {
        // Category category = categoryDAO.getCategoryById(5);
        // if (category != null) {

        // System.out.println(category.getName());
        // System.out.println(category.getId());
        // category.setName("Doces UPDATE");
        // System.out.println(category.getName());
        // System.out.println(category.getId());
        // categoryDAO.updateCategory(category);
        // } else {
        // System.out.println("Categoria não encontrada");
        // }
        // } catch (Exception e) {
        // System.err.println(e);
        // }
        categoryDAO.removeCategory(6);

    }
}
