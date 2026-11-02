package io.github.httpaline.restaurante.app;

import io.github.httpaline.restaurante.cardapio.ItemCardapio;
import io.github.httpaline.restaurante.cardapio.Lanche;
import io.github.httpaline.restaurante.pedido.Pedido;

public class Main {
    static void main() {
        MenuConsole menu = new MenuConsole();
        RestauranteFacade sistema = new RestauranteFacade();

        int opcao = -1;
        while (opcao != 0) {
            menu.exibirMenu();
            opcao = menu.lerOpcao();
            switch (opcao) {
                case 1 -> {
                    Pedido pedido = sistema.criarPedido(menu.lerNomeCliente());
                    do {
                        ItemCardapio item = getItem(menu, sistema);

                        sistema.adicionarItem(pedido,item);

                    } while (menu.perguntarMaisItem());
                    menu.exibirPedido(pedido);
                }

                case 2 -> menu.exibirPedidos(sistema.getPedidos());

                case 3 -> {
                    Pedido pedido = sistema.buscarPedido(menu.lerOpcao("Número do pedido: "));
                    if (pedido == null){
                        menu.exibirMensagem("Pedido não encontrado.");
                    }else{
                        menu.exibirPedido(pedido);
                        menu.exibirMenuModificacao();

                        switch(menu.lerOpcao()){
                            case 1 ->{
                                ItemCardapio item = getItem(menu,sistema);
                                sistema.adicionarItem(pedido,item);
                                menu.exibirPedido(pedido);
                            }
                            case 2 ->{
                                sistema.removerItem(pedido, menu.lerOpcao("Número do item a remover: "));
                                menu.exibirPedido(pedido);
                            }
                            case 3 ->{
                                sistema.cancelarPedido(pedido.getNumero());
                                menu.exibirMensagem("Pedido cancelado.");
                            }
                            case 0 -> menu.exibirMensagem("Voltando ao menu...");
                            default -> menu.exibirMensagem("Opção inválida.");
                        }
                    }
                }

                case 4 -> {
                    double total = sistema.calcularTotalDoDia();
                    menu.exibirFechamentoDoDia(total);
                    opcao = 0;
                }

                case 0 -> menu.exibirMensagem("Até logo!");
                default -> menu.exibirMensagem("Opção inválida.");
            }
        }
    }

    private static ItemCardapio getItem(MenuConsole menu, RestauranteFacade sistema) {
        menu.exibirCardapio();
        int opcaoCardapio = menu.lerOpcao();
        Lanche lanche = sistema.criarLanche(opcaoCardapio);

        if(menu.perguntarSimNao("Deseja retirar algum ingrediente?")){
            menu.exibirIngredientes();
            sistema.retirarIngredientes(lanche, menu.lerIngredientesParaRetirar());
        }

        ItemCardapio item = lanche;

        int opcaoAdicional = -1;
        do {
            menu.exibirAdicionais();
            opcaoAdicional = menu.lerOpcao();
            if (opcaoAdicional != 0) {
                item = sistema.adicionarAdicional(item, opcaoAdicional);
            }
        } while (opcaoAdicional != 0);
        return item;
    }
}
