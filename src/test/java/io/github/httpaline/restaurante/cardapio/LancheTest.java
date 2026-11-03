package io.github.httpaline.restaurante.cardapio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LancheTest {
    @Test
    void precoNegativoEhRecusado(){
        assertThrows(IllegalArgumentException.class, () -> new Lanche("X-Burger", -1.0));
    }


    @Test
    void nomeVazioEhRecusado() {
        assertThrows(IllegalArgumentException.class, () -> new Lanche("", 16.0));
        assertThrows(IllegalArgumentException.class, () -> new Lanche("   ", 16.0));
        assertThrows(IllegalArgumentException.class, () -> new Lanche(null, 16.0));
    }

    @Test
    void retirarIngredientesMudaDescricaoMasNaoPreco() {
        Lanche lanche = LancheFactory.xBacon();

        lanche.setTomate(false);
        lanche.setCebola(false);

        assertEquals("X-Bacon sem tomate sem cebola", lanche.getDescricao());
        assertEquals(22.0, lanche.getPreco(), 0.001);
    }

    @Test
    void personalizarUmLancheNaoAfetaOutro() {
        Lanche primeiro = LancheFactory.xBacon();
        Lanche segundo = LancheFactory.xBacon();

        primeiro.setCebola(false);

        assertNotSame(primeiro, segundo);
        assertEquals("X-Bacon", segundo.getDescricao());
    }


}