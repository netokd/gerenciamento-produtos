package com.gerenciamento.produtos;

public class TestCategoryDAO {

    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();
        // Category category = new Category("Salgado");
        // categoryDAO.insertCategory(category);

        // categoryDAO.removeCategory(1);

        Category category = categoryDAO.getCategoryById(3);
        System.out.println(category.getName());
        System.out.println(category.getId());
        category.setName("Salgados UPDATE");
        System.out.println(category.getName());
        System.out.println(category.getId());
        categoryDAO.updateCategory(category);

    }
}
