package com.erick.comanda.service;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ComandaServiceTest {

    @Mock
    private ComandaService comandaBean;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveAdicionarItemNaComanda() {
        //ComandaService comandaServiceMock = mock(ComandaService.class);

        /* ItemComandaDTO itemComanda = new ItemComandaDTO(null, 1, 1);
        
        Assertions.assertDoesNotThrow(() -> {
            comandaBean.adicionarItemNaComanda(itemComanda, 12);
        }); */
    }
}