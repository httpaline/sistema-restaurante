package io.github.httpaline.restaurante.cardapio;

public class ComCheddar implements ItemCardapio {
    private final ItemCardapio itemEmbrulhado;

    public ComCheddar(ItemCardapio itemEmbrulhado) {
        this.itemEmbrulhado = itemEmbrulhado;
    }

    @Override
    public String getDescricao() {
        return itemEmbrulhado.getDescricao() + " + cheddar";
    }

    @Override
    public double getPreco() {
        return itemEmbrulhado.getPreco() + 4.00;
    }
}
