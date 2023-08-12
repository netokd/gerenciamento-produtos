package com.gerenciamento.produtos;

public class TestCategoryDAO {

    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();
        // Category category = new Category("Salgado");
        // categoryDAO.insertCategory(category);

        // categoryDAO.removeCategory(1);

        Category category = categoryDAO.getCategoryById(2);
        System.out.println(category.getName());
        category.setName("Salgados UPDATE");
        System.out.println(category.getName());
        categoryDAO.updateCategory(category);

    }
}
