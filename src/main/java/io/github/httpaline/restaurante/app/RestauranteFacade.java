package io.github.httpaline.restaurante.app;

import io.github.httpaline.restaurante.cardapio.Lanche;
import io.github.httpaline.restaurante.cardapio.LancheFactory;
import io.github.httpaline.restaurante.pedido.GerenciadorPedidos;
import io.github.httpaline.restaurante.pedido.Pedido;

import java.util.Collection;


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

    public void adicionarLanche(Pedido pedido, int opcao) {
        Lanche lanche = switch (opcao) {
            case 1 -> LancheFactory.xBurguer();
            case 2 -> LancheFactory.xSalada();
            case 3 -> LancheFactory.xBacon();
            default -> throw new IllegalArgumentException("Opção de lanche inválida.");
        };

        pedido.adicionarItem(lanche);
    }
}
