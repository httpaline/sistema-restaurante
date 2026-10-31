package io.github.httpaline.restaurante.app;

import io.github.httpaline.restaurante.pedido.Pedido;

public class Main {
    static void main() {
        MenuConsole menu = new MenuConsole();
        RestauranteFacade sistema = new RestauranteFacade();

        int opcao = -1;
        while(opcao != 0){
            menu.exibirMenu();
            opcao = menu.lerOpcao();
            switch (opcao){
                case 1 ->{
                        Pedido pedido = sistema.criarPedido(menu.lerNomeCliente());
                        do{
                            menu.exibirCardapio();
                            int opcaoCardapio = menu.lerOpcao();
                            sistema.adicionarLanche(pedido,opcaoCardapio);
                        }while(menu.perguntarMaisItem());
                        menu.exibirPedido(pedido);
                }

                case 2 -> menu.exibirPedidos(sistema.getPedidos());

                case 3 -> {
                    double total = sistema.calcularTotalDoDia();
                    menu.exibirFechamentoDoDia(total);
                    opcao = 0;
                }

                case 0 -> menu.exibirMensagem("Até logo!");
                default -> menu.exibirMensagem("Opção inválida.");
            }
        }
    }
}
