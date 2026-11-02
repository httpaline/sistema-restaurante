package io.github.httpaline.restaurante.cardapio;

public class Lanche implements ItemCardapio {
    private String nome;
    private double preco;
    private boolean queijo = true;
    private boolean alface = true;
    private boolean tomate = true;
    private boolean cebola = true;

    public boolean isQueijo() {
        return queijo;
    }

    public boolean isAlface() {
        return alface;
    }

    public boolean isTomate() {
        return tomate;
    }

    public boolean isCebola() {
        return cebola;
    }

    @Override
    public String getDescricao() {
        String descricao = nome;
        if (!queijo) descricao += " sem queijo";
        if (!alface) descricao += " sem alface";
        if (!tomate) descricao += " sem tomate";
        if (!cebola) descricao += " sem cebola";
        return descricao;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public Lanche(String nome, double preco) {
        setNome(nome);
        setPreco(preco);
    }

    public void setQueijo(boolean queijo) {
        this.queijo = queijo;
    }

    public void setAlface(boolean alface) {
        this.alface = alface;
    }

    public void setTomate(boolean tomate) {
        this.tomate = tomate;
    }

    public void setCebola(boolean cebola) {
        this.cebola = cebola;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio ou nulo.");
        }
        this.nome = nome;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        this.preco = preco;
    }
}
