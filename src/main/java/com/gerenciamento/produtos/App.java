package com.gerenciamento.produtos;

import java.util.Scanner;

/**
 * Implementação do Menu, que chama os metodos que estão em ProductManager.java
 * Para melhorar a semantica o tratamento e as linhas de comando estão em
 * productManager.java
 * Futuramente devo implemenar ou uma interface grafica ou em web e devo retirar
 * os input e output do ProductManager
 */
public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int menu;

        do {
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Atualizar Produto");
            System.out.println("4 - Remover Produto");
            System.out.println("0 - Sair");
            System.out.println("Escolha o numero da opção: \n");

            menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1:
                    ProductManager.insertProduct(args, sc);
                    break;
                case 2:
                    ProductManager.listProducts(args, 0);
                    break;
                case 3:
                    ProductManager.updateProduct(args, sc);
                    break;
                case 4:
                    ProductManager.removeProduct(args, sc);
                    break;
                case 0:
                    System.out.println("Encerrando Programa!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (menu != 0);

        sc.close();
    }
}
