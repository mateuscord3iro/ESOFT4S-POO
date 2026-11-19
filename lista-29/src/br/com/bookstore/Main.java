package br.com.bookstore;

import br.com.bookstore.dao.LivroDAO;
import br.com.bookstore.dao.LivroDAOSqlite;
import br.com.bookstore.model.Livro;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        LivroDAO dao = new LivroDAOSqlite();

        Livro livro1 = new Livro("Clean Code", "Robert C. Martin", 119.90);
        Livro livro2 = new Livro("Design Patterns", "Erich Gamma e outros", 149.90);
        Livro livro3 = new Livro("Refactoring", "Martin Fowler", 129.90);

        dao.salvar(livro1);
        dao.salvar(livro2);
        dao.salvar(livro3);

        System.out.println("IDs gerados:");
        System.out.println(livro1.getId());
        System.out.println(livro2.getId());
        System.out.println(livro3.getId());

        System.out.println("\nConsulta do livro com ID 2:");
        dao.buscarPorId(2L).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Livro de ID 2 nao encontrado.")
        );

        System.out.println("\nConsulta do livro com ID 999:");
        dao.buscarPorId(999L).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Livro de ID 999 nao encontrado.")
        );

        System.out.println("\nCatalogo de livros:");
        System.out.printf("%-5s | %-25s | %-25s | %12s%n", "ID", "TITULO", "AUTOR", "PRECO");
        System.out.println("-".repeat(76));
        for (Livro livro : dao.listarTodos()) {
            System.out.printf(Locale.forLanguageTag("pt-BR"), "%-5d | %-25s | %-25s | R$ %9.2f%n",
                    livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getPreco());
        }
    }
}
