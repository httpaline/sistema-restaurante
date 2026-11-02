package io.github.httpaline.restaurante.cardapio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AdicionaisTest {
    @Test
    void xBaconComCheddarCusta26() {
        ItemCardapio lanche = new ComCheddar(LancheFactory.xBacon());
        assertEquals(26.0, lanche.getPreco(), 0.001);
    }

    @Test
    void xBaconComCheddarEBaconExtraTemDescricaoEPrecoCorretos() {
        ItemCardapio lanche = new ComBaconExtra(new ComCheddar(LancheFactory.xBacon()));

        assertEquals("X-Bacon + cheddar + bacon extra", lanche.getDescricao());
        assertEquals(32.0, lanche.getPreco(), 0.001);
    }


    @Test
    void ordemDosAdicionaisMudaDescricaoMasNaoPreco() {
        ItemCardapio cheddarPrimeiro = new ComBaconExtra(new ComCheddar(LancheFactory.xBacon()));
        ItemCardapio baconPrimeiro = new ComCheddar(new ComBaconExtra(LancheFactory.xBacon()));

        assertEquals(cheddarPrimeiro.getPreco(), baconPrimeiro.getPreco(), 0.001);
        assertNotEquals(cheddarPrimeiro.getDescricao(), baconPrimeiro.getDescricao());
    }
}
