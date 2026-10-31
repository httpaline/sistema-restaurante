package io.github.httpaline.restaurante.app;

import io.github.httpaline.restaurante.cardapio.ItemCardapio;
import io.github.httpaline.restaurante.cardapio.Lanche;
import io.github.httpaline.restaurante.pedido.Pedido;

import java.util.Collection;
import java.util.Scanner;

public class MenuConsole {
    private static final String LINHA_DUPLA = "================================";
    private static final String LINHA_SIMPLES = "--------------------------------";
    Scanner sc = new Scanner(System.in);

    public void exibirMenu() {
        System.out.println();
        System.out.println(LINHA_DUPLA);
        System.out.println("          RESTAURANTE");
        System.out.println(LINHA_DUPLA);
        System.out.println(" [1] Registrar novo pedido");
        System.out.println(" [2] Listar pedidos do dia");
        System.out.println(" [3] Fechar restaurante");
        System.out.println(LINHA_SIMPLES);
        System.out.println(" [0] Sair");
        System.out.println(LINHA_DUPLA);
        System.out.print(" Opção: ");
    }

    public void exibirCardapio() {
        System.out.println();
        System.out.println(LINHA_SIMPLES);
        System.out.println("            CARDÁPIO");
        System.out.println(LINHA_SIMPLES);
        System.out.println(" [1] X-Burger          R$  16,00");
        System.out.println(" [2] X-Salada          R$  18,00");
        System.out.println(" [3] X-Bacon           R$  22,00");
        System.out.println(LINHA_SIMPLES);
        System.out.print(" Escolha o lanche: ");
    }

    public int lerOpcao() {
        int opcao = sc.nextInt();
        sc.nextLine();
        return opcao;
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public boolean perguntarMaisItem() {
        System.out.println();
        System.out.print("Adicionar mais um item? (S/N): ");
        String resposta = sc.nextLine();
        return resposta.equalsIgnoreCase("S");
    }

    public String lerNomeCliente() {
        System.out.println();
        System.out.print("Nome do cliente: ");
        return sc.nextLine();
    }

    public void exibirPedido(Pedido pedido) {
        System.out.println();
        System.out.println(LINHA_DUPLA);
        System.out.println("          PEDIDO Nº " + pedido.getNumero());
        System.out.println(LINHA_DUPLA);
        System.out.println(" Cliente: " + pedido.getNomeCliente());
        System.out.println(LINHA_SIMPLES);

        int numeroItem = 1;
        for (ItemCardapio item : pedido.getItens()) {
            System.out.printf(" %d. %-18s R$ %6.2f%n", numeroItem, item.getDescricao(), item.getPreco());
            numeroItem++;
        }

        System.out.println(LINHA_SIMPLES);
        System.out.printf(" %-21s R$ %6.2f%n", "TOTAL", pedido.calcularTotal());
        System.out.println(LINHA_DUPLA);
    }

    public void exibirFechamentoDoDia(double total) {
        System.out.println();
        System.out.println(LINHA_DUPLA);
        System.out.println("       FECHAMENTO DO DIA");
        System.out.println(LINHA_DUPLA);
        System.out.printf(" %-21s R$ %6.2f%n", "Total vendido", total);
        System.out.println(LINHA_DUPLA);
        System.out.println("Restaurante fechado. Até amanhã!");
        System.out.println(LINHA_DUPLA);
    }

    public void exibirPedidos(Collection<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("Não há pedidos!");
            return;
        }
        for (Pedido pedido : pedidos) {
            exibirPedido(pedido);
        }
    }
}
