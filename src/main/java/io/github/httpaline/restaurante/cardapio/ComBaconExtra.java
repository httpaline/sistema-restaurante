package io.github.httpaline.restaurante.cardapio;

public class ComBaconExtra implements ItemCardapio {
    private final ItemCardapio itemEmbrulhado;

    public ComBaconExtra(ItemCardapio itemEmbrulhado) {
        this.itemEmbrulhado = itemEmbrulhado;
    }

    @Override
    public String getDescricao() {
        return itemEmbrulhado.getDescricao() + " + bacon extra";
    }

    @Override
    public double getPreco() {
        return itemEmbrulhado.getPreco() + 6.00;
    }
}
