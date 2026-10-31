package io.github.httpaline.restaurante.pedido;

import io.github.httpaline.restaurante.cardapio.Lanche;

import java.util.ArrayList;

public class Pedido {
    private final int numero;
    private String nomeCliente;
    ArrayList<Lanche> itens = new ArrayList<>();

    public Pedido(int numero, String nomeCliente) {
        this.numero = numero;
        setNomeCliente(nomeCliente);
    }

    public void setNomeCliente(String nomeCliente) {
        if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio ou nulo.");
        }
        this.nomeCliente = nomeCliente;
    }

    public int getNumero() {
        return numero;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void adicionarItem(Lanche lanche) {
        itens.add(lanche);
    }

    public ArrayList<Lanche> getItens() {
        return itens;
    }

    public double calcularTotal() {
        double soma = 0.0;
        for (Lanche lanche : itens) {
            soma += lanche.getPreco();
        }
        return soma;
    }
}
