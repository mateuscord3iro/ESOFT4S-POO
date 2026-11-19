package br.com.starlog;

import br.com.starlog.dao.ProdutoDAO;
import br.com.starlog.dao.ProdutoDAOSqlite;
import br.com.starlog.model.Produto;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAOSqlite();

        Produto teclado = new Produto("Teclado Mecanico RGB", 319.90, 10);
        Produto mousepad = new Produto("Mousepad Speed XL", 59.90, 25);

        dao.salvar(teclado);
        dao.salvar(mousepad);

        System.out.println("IDs gerados:");
        System.out.println("Teclado: " + teclado.getId());
        System.out.println("Mousepad: " + mousepad.getId());

        System.out.println("\nCatalogo inicial:");
        mostrarTabela(dao.listarTodos());

        teclado.setPreco(289.90);
        teclado.setEstoque(15);
        boolean atualizado = dao.atualizar(teclado);
        System.out.println("\nAtualizacao do teclado: " + atualizado);
        dao.buscarPorId(teclado.getId()).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Teclado nao encontrado."));

        boolean excluido = dao.excluir(mousepad.getId());
        System.out.println("\nExclusao do mousepad: " + excluido);
        boolean inexistente = dao.excluir(888L);
        System.out.println("Exclusao do ID 888: " + inexistente);
        if (!inexistente) {
            System.out.println("O produto 888 nao existe. Nenhum registro foi excluido.");
        }

        System.out.println("\nCatalogo final:");
        List<Produto> produtos = dao.listarTodos();
        mostrarTabela(produtos);
        System.out.println("Total de produtos cadastrados: " + produtos.size());
    }

    private static void mostrarTabela(List<Produto> produtos) {
        System.out.printf("%-5s | %-24s | %-12s | %-8s%n", "ID", "NOME", "PRECO", "ESTOQUE");
        System.out.println("-".repeat(61));
        for (Produto produto : produtos) {
            System.out.printf(Locale.forLanguageTag("pt-BR"), "%-5d | %-24s | R$ %9.2f | %8d%n",
                    produto.getId(), produto.getNome(), produto.getPreco(), produto.getEstoque());
        }
    }
}
