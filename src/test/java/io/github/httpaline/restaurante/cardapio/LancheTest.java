package io.github.httpaline.restaurante.cardapio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LancheTest {
    @Test
    void precoNegativoEhRecusado(){
        assertThrows(IllegalArgumentException.class, () -> new Lanche("X-Burger", -1.0));
    }


}