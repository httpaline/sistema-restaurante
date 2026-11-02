package io.github.httpaline.restaurante.app;

import io.github.httpaline.restaurante.cardapio.*;
import io.github.httpaline.restaurante.pedido.GerenciadorPedidos;
import io.github.httpaline.restaurante.pedido.Pedido;

import java.util.Collection;
import java.util.Set;

public class RestauranteFacade {
    private final GerenciadorPedidos gerenciador = new GerenciadorPedidos();

    public Pedido criarPedido(String nomeCliente) {
        return gerenciador.criarPedido(nomeCliente);
    }

    public Collection<Pedido> getPedidos() {
        return gerenciador.getPedidos();
    }

    public double calcularTotalDoDia() {
        return gerenciador.calcularTotalDoDia();
    }

    public Lanche criarLanche(int opcao) {
        return switch (opcao) {
            case 1 -> LancheFactory.xBurguer();
            case 2 -> LancheFactory.xSalada();
            case 3 -> LancheFactory.xBacon();
            default -> throw new IllegalArgumentException("Opção de lanche inválida.");
        };
    }

    public void adicionarItem(Pedido pedido, ItemCardapio item) {
       pedido.adicionarItem(item);
    }

    public ItemCardapio adicionarAdicional(ItemCardapio item, int opcao) {
        return switch (opcao) {
            case 1 -> new ComCheddar(item);
            case 2 -> new ComBaconExtra(item);
            default -> throw new IllegalArgumentException("Opção inválida!");
        };
    }

    public void retirarIngredientes(Lanche lanche, Set<Integer> ingredientes){
        for(int numero : ingredientes){
            switch (numero){
                case 1 -> lanche.setQueijo(false);
                case 2 -> lanche.setAlface(false);
                case 3 -> lanche.setTomate(false);
                case 4 -> lanche.setCebola(false);
                default -> throw new IllegalArgumentException("Opção inválida!");
            }
        }
    }

    public Pedido buscarPedido(int numero){
        return gerenciador.buscarPedido(numero);
    }

    public void removerItem(Pedido pedido, int numeroItem){
        pedido.removerItem(numeroItem);
    }

    public void cancelarPedido(int numeroPedido){
        gerenciador.cancelarPedido(numeroPedido);
    }


}
