package gerenciamento.vampiro.api.core.domain.vampiro.valueObject;

import gerenciamento.vampiro.api.core.domain.vampiro.exception.EspecieVampiroInvalidaException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EspecieTest {

    @ParameterizedTest
    @ValueSource(strings = {"VAMPIRO", "HIBRIDO", "ORIGINAL"})
    void deveCriarEspecieValidaMaiusculo(String valor) {
        Especie especie = Especie.build(valor);
        assertEquals(valor, especie.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"vampiro", "hibrido", "original"})
    void deveCriarEspecieValidaMinusculo(String valor) {
        Especie especie = Especie.build(valor);
        assertNotNull(especie);
    }

    @Test
    void deveLancarExcecaoQuandoEspecieInvalida() {
        assertThrows(EspecieVampiroInvalidaException.class, () -> Especie.build("HUMANO"));
    }

    @Test
    void deveLancarExcecaoQuandoEspecieVazia() {
        assertThrows(EspecieVampiroInvalidaException.class, () -> Especie.build(""));
    }
}
