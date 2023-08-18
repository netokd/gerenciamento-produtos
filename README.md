# gerenciamento-produtos
Criação de programa em java, com banco de dados local mysql de gerenciamento de produtos para treinar o uso de banco de dados e DAO e meus primeiros CRUDS em java


Banco de dados local em Mysql

em App.java temos o menu, que chama os metodos estaticos em ProductManager, onde nele interage com o usuario via linha de comando.

ProductManager, manipulação de produtos e categorias, porem não interagem diretamente com banco de dados

ProductDAO e CategoryDAO Classes que interagem com o banco de dados, trazendo todas as informações necessarias para o ProductManager.java

Category.java e Product.java definição das classes, com os respectivos construtores, getters e setters.

TestCategoryDAO e TestProductDAO arquivos criados apenas para testar os DAOs pois a ultima etapa foi a interação de fato com o banco de dados com as entradas e saidas para o usuario.


A ideia futuramente é implementar uma interface grafica ou web, para Apresentar e receber melhor os dados, tornando mais intuitivo e funcional para todos. Estarei estudando para futuramente fazer as atualizações necessarias ou reescrever o codigo como um projeto Web