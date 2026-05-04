package gerenciamento.vampiro.api.core.domain.vampiro.valueObject;

import gerenciamento.vampiro.api.core.domain.vampiro.exception.IdadeInvalidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdadeTest {

    @Test
    void deveCriarIdadeValida() {
        Idade idade = Idade.build(100);
        assertEquals(100, idade.getValue());
    }

    @Test
    void deveCriarIdadeMinima() {
        Idade idade = Idade.build(1);
        assertEquals(1, idade.getValue());
    }

    @Test
    void deveLancarExcecaoQuandoIdadeZero() {
        assertThrows(IdadeInvalidaException.class, () -> Idade.build(0));
    }

    @Test
    void deveLancarExcecaoQuandoIdadeNegativa() {
        assertThrows(IdadeInvalidaException.class, () -> Idade.build(-500));
    }
}
