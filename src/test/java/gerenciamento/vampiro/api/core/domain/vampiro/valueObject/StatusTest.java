package gerenciamento.vampiro.api.core.domain.vampiro.valueObject;

import gerenciamento.vampiro.api.core.domain.vampiro.exception.StatusInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @ParameterizedTest
    @ValueSource(strings = {"ATIVO", "ADORMECIDO", "MORTO"})
    void deveCriarStatusValidoMaiusculo(String valor) {
        Status status = Status.build(valor);
        assertEquals(valor, status.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ativo", "adormecido", "morto"})
    void deveCriarStatusValidoMinusculo(String valor) {
        Status status = Status.build(valor);
        assertNotNull(status);
    }

    @Test
    void deveLancarExcecaoQuandoStatusInvalido() {
        assertThrows(StatusInvalidoException.class, () -> Status.build("INATIVO"));
    }

    @Test
    void deveLancarExcecaoQuandoStatusVazio() {
        assertThrows(StatusInvalidoException.class, () -> Status.build(""));
    }
}
