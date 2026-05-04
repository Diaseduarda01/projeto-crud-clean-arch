package gerenciamento.vampiro.api.core.application.usecase.vampiro;

import gerenciamento.vampiro.api.core.application.repository.vampiro.BuscarVampiroPorId;
import gerenciamento.vampiro.api.core.application.usecase.shared.RegistroNaoEncontradoException;
import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarPorIdUseCaseTest {

    @Mock
    private BuscarVampiroPorId buscarVampiroPorId;

    @InjectMocks
    private ListarPorIdUseCase useCase;

    @Test
    void deveRetornarVampiroQuandoEncontrado() {
        UUID id = UUID.randomUUID();
        Vampiro vampiro = Vampiro.build("Drácula", 500, "VAMPIRO", "ATIVO", "Transilvânia", false);
        when(buscarVampiroPorId.findById(id)).thenReturn(Optional.of(vampiro));

        Vampiro resultado = useCase.executar(id);

        assertNotNull(resultado);
        assertEquals("Drácula", resultado.getNome());
        verify(buscarVampiroPorId, times(1)).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrado() {
        UUID id = UUID.randomUUID();
        when(buscarVampiroPorId.findById(id)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> useCase.executar(id));
    }
}
