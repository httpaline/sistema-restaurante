package io.github.httpaline.restaurante.app;

import io.github.httpaline.restaurante.cardapio.ItemCardapio;
import io.github.httpaline.restaurante.pedido.Pedido;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
        System.out.println(" [3] Fechar restaurante");
        System.out.println(LINHA_SIMPLES);
        System.out.println(" [0] Sair");
        System.out.println(LINHA_DUPLA);
        System.out.print(" Opção: ");
    }

    public void exibirCardapio() {
        System.out.println();
        System.out.println(LINHA_DUPLA);
        System.out.println("            CARDÁPIO");
        System.out.println(LINHA_DUPLA);
        System.out.println(" [1] X-Burger          R$  16,00");
        System.out.println(" [2] X-Salada          R$  18,00");
        System.out.println(" [3] X-Bacon           R$  22,00");
        System.out.println(LINHA_DUPLA);
        System.out.print(" Escolha o lanche: ");
    }

    public void exibirAdicionais(){
        System.out.println();
        System.out.println(LINHA_DUPLA);
        System.out.println("           ADICIONAIS");
        System.out.println(LINHA_DUPLA);
        System.out.println(" [1] Cheddar         + R$   4,00");
        System.out.println(" [2] Bacon extra     + R$   6,00");
        System.out.println(LINHA_SIMPLES);
        System.out.println(" [0] Nenhum / finalizar");
        System.out.println(LINHA_DUPLA);
        System.out.print(" Escolha o adicional: ");
    }

    public void exibirIngredientes() {
        System.out.println();
        System.out.println(LINHA_DUPLA);
        System.out.println("      RETIRAR INGREDIENTES");
        System.out.println(LINHA_DUPLA);
        System.out.println(" [1] Queijo");
        System.out.println(" [2] Alface");
        System.out.println(" [3] Tomate");
        System.out.println(" [4] Cebola");
        System.out.println(LINHA_SIMPLES);
        System.out.println(" Números separados por vírgula");
        System.out.println(" (ex: 3,4) ou Enter para nenhum");
        System.out.println(LINHA_DUPLA);
        System.out.print(" Retirar: ");
    }

    public Set<Integer> lerIngredientesParaRetirar(){
        Set<Integer> numeros = new HashSet<>();
        String linha = sc.nextLine().trim();

        if(linha.isEmpty()){
            return numeros;
        }

        String[] partes = linha.split(",");

        for(String parte : partes){
            numeros.add(Integer.parseInt(parte.trim()));
        }

        return numeros;
    }

    public int lerOpcao() {
        int opcao = sc.nextInt();
        sc.nextLine();
        return opcao;
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

    public void exibirPedido(Pedido pedido) {
        System.out.println();
        System.out.println(LINHA_DUPLA);
        System.out.println("          PEDIDO Nº " + pedido.getNumero());
        System.out.println(LINHA_DUPLA);
        System.out.println(" Cliente: " + pedido.getNomeCliente());
        System.out.println(LINHA_SIMPLES);

        int numeroItem = 1;
        for (ItemCardapio item : pedido.getItens()) {
            String[] partes = item.getDescricao().split(" \\+ ");
            String[] retiradas = partes[0].split(" sem ");
            System.out.printf(" %d. %-18s R$ %6.2f%n", numeroItem, retiradas[0], item.getPreco());

            for(int i=1;i<retiradas.length;i++){
                System.out.println("      - " + retiradas[i]);
            }

            for(int i=1; i<partes.length;i++){
                System.out.println("      + " + partes[i]);
            }

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
