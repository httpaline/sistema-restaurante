package io.github.httpaline.restaurante.app;
import io.github.httpaline.restaurante.cardapio.ItemCardapio;
import io.github.httpaline.restaurante.pedido.Pedido;

import java.util.*;

public class MenuConsole {
    private static final String LINHA_DUPLA = "================================";
    private static final String LINHA_SIMPLES = "--------------------------------";
    private final Scanner sc = new Scanner(System.in);

    public void exibirMenu() {
        System.out.println();
        System.out.println(LINHA_DUPLA);
        System.out.println("          RESTAURANTE");
        System.out.println(LINHA_DUPLA);
        System.out.println(" [1] Registrar novo pedido");
        System.out.println(" [2] Listar pedidos do dia");
        System.out.println(" [3] Modificar pedido");
        System.out.println(" [4] Fechar restaurante");
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

    public void exibirAdicionais() {
        System.out.println();
        System.out.println(LINHA_SIMPLES);
        System.out.println("           ADICIONAIS");
        System.out.println(LINHA_SIMPLES);
        System.out.println(" [1] Cheddar         + R$   4,00");
        System.out.println(" [2] Bacon extra     + R$   6,00");
        System.out.println(LINHA_SIMPLES);
        System.out.println(" (ex: 1,2) ou Enter para nenhum");
        System.out.print(" Adicionar: ");
    }

    public void exibirIngredientes() {
        System.out.println();
        System.out.println(LINHA_SIMPLES);
        System.out.println("      RETIRAR INGREDIENTES");
        System.out.println(LINHA_SIMPLES);
        System.out.println(" [1] Queijo");
        System.out.println(" [2] Alface");
        System.out.println(" [3] Tomate");
        System.out.println(" [4] Cebola");
        System.out.println(LINHA_SIMPLES);
        System.out.println(" Números separados por vírgula");
        System.out.println(" (ex: 3,4) ou Enter para nenhum");
        System.out.println(LINHA_SIMPLES);
        System.out.print(" Retirar: ");
    }

    public void exibirMenuModificacao() {
        System.out.println();
        System.out.println(LINHA_SIMPLES);
        System.out.println("        MODIFICAR PEDIDO");
        System.out.println(LINHA_SIMPLES);
        System.out.println(" [1] Acrescentar item");
        System.out.println(" [2] Remover item");
        System.out.println(" [3] Cancelar pedido");
        System.out.println(LINHA_SIMPLES);
        System.out.println(" [0] Voltar");
        System.out.println(LINHA_SIMPLES);
        System.out.print(" Opção: ");
    }

    private List<Integer> lerNumeros() {
        while (true) {
            List<Integer> numeros = new ArrayList<>();
            String linha = sc.nextLine().trim();

            if (linha.isEmpty()) {
                return numeros;
            }

            try {
                for (String parte : linha.split(",")) {
                    numeros.add(Integer.parseInt(parte.trim()));
                }
                return numeros;
            } catch (NumberFormatException e) {
                System.out.print(" Use apenas números separados por vírgula: ");
            }
        }
    }

    public Set<Integer> lerIngredientesParaRetirar() {
        return new HashSet<>(lerNumeros());
    }

    public List<Integer> lerAdicionais() {
        return lerNumeros();
    }

    public int lerOpcao() {
        while (true) {
            try {
                int opcao = sc.nextInt();
                sc.nextLine();
                return opcao;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.print(" Digite apenas números: ");
            }
        }
    }

    public int lerOpcao(String pergunta){
        System.out.print(" " + pergunta + " ");
        return lerOpcao();
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(" " + mensagem);
    }

    public boolean perguntarSimNao(String pergunta) {
        System.out.print(" " + pergunta + " (S/N): ");
        String resposta = sc.nextLine();
        return resposta.equalsIgnoreCase("S");
    }

    public boolean perguntarMaisItem(){
        System.out.println();
        return perguntarSimNao("Adicionar mais um item?");
    }

    public String lerNomeCliente() {
        System.out.println();
        System.out.print(" Nome do cliente: ");
        return sc.nextLine();
    }

    private String nomeBase(String descricao) {
        return descricao.split(" \\+ ")[0].split(" sem ")[0];
    }

    private void exibirDetalhes(String descricao) {
        String[] partes = descricao.split(" \\+ ");
        String[] retiradas = partes[0].split(" sem ");

        for (int i = 1; i < retiradas.length; i++) {
            System.out.println("      - sem " + retiradas[i]);
        }
        for (int i = 1; i < partes.length; i++) {
            System.out.println("      + " + partes[i]);
        }
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
            String descricao = item.getDescricao();
            System.out.printf(" %d. %-18s R$ %6.2f%n", numeroItem, nomeBase(descricao), item.getPreco());
            exibirDetalhes(descricao);
            numeroItem++;
        }

        System.out.println(LINHA_SIMPLES);
        System.out.printf(" %-21s R$ %6.2f%n", "TOTAL", pedido.calcularTotal());
        System.out.println(LINHA_DUPLA);
    }

    public void exibirFechamentoDoDia(double total, Set<String> combinacoes) {
        System.out.println();
        System.out.println(LINHA_DUPLA);
        System.out.println("       FECHAMENTO DO DIA");
        System.out.println(LINHA_DUPLA);
        System.out.printf(" %-21s R$ %6.2f%n", "Total vendido", total);
        System.out.println(LINHA_SIMPLES);
        System.out.println(" Combinações diferentes: " + combinacoes.size());
        System.out.println(LINHA_SIMPLES);

        for (String combinacao : combinacoes) {
            System.out.println(" - " + nomeBase(combinacao));
            exibirDetalhes(combinacao);
        }

        System.out.println(LINHA_DUPLA);
        System.out.println("Restaurante fechado. Até amanhã!");
        System.out.println(LINHA_DUPLA);
    }

    public void exibirPedidos(Collection<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println(" Não há pedidos!");
            return;
        }
        for (Pedido pedido : pedidos) {
            exibirPedido(pedido);
        }
    }
}
