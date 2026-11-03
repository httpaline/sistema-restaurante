package io.github.httpaline.restaurante.pedido;

import io.github.httpaline.restaurante.cardapio.ItemCardapio;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GerenciadorPedidos {
    private final HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 1;

    public Pedido criarPedido(String nomeCliente) {
        Pedido novo = new Pedido(contadorPedidos, nomeCliente);
        pedidos.put(novo.getNumero(), novo);
        contadorPedidos++;
        return novo;
    }

    public Pedido buscarPedido(int numero) {
        return pedidos.get(numero);
    }

    public void cancelarPedido(int numero){
        if(!pedidos.containsKey(numero)){
            throw new IllegalArgumentException("Pedido inexistente: " + numero);
        }
        pedidos.remove(numero);
    }

    public double calcularTotalDoDia() {
        double total = 0.0;
        for (Pedido pedido : pedidos.values()) {
            total += pedido.calcularTotal();
        }
        return total;
    }

    public Set<String> getCombinacoesDoDia(){
        Set<String> combinacoes = new HashSet<>();

        for(Pedido pedido : pedidos.values()){
            for(ItemCardapio item : pedido.getItens()){
                combinacoes.add(item.getDescricao());
            }
        }

        return combinacoes;
    }

    public Collection<Pedido> getPedidos() {
        return pedidos.values();
    }
}


