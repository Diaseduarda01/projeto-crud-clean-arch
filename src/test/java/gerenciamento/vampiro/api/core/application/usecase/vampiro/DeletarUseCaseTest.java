package gerenciamento.vampiro.api.core.application.usecase.vampiro;

import gerenciamento.vampiro.api.core.application.repository.vampiro.DeletarVampiroPorId;
import gerenciamento.vampiro.api.core.application.usecase.shared.RegistroNaoEncontradoException;
import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarUseCaseTest {

    @Mock
    private DeletarVampiroPorId deletarVampiroPorId;

    @Mock
    private ListarPorIdUseCase listarPorIdUseCase;

    @InjectMocks
    private DeletarUseCase useCase;

    @Test
    void deveDeletarVampiroComSucesso() {
        UUID id = UUID.randomUUID();
        Vampiro vampiro = Vampiro.build("Drácula", 500, "VAMPIRO", "ATIVO", "Transilvânia", false);
        when(listarPorIdUseCase.executar(id)).thenReturn(vampiro);

        String resultado = useCase.executar(id);

        assertNotNull(resultado);
        assertTrue(resultado.contains(vampiro.getId().toString()));
        verify(deletarVampiroPorId, times(1)).deletarById(vampiro.getId());
    }

    @Test
    void deveLancarExcecaoQuandoVampiroNaoEncontrado() {
        UUID id = UUID.randomUUID();
        when(listarPorIdUseCase.executar(id)).thenThrow(new RegistroNaoEncontradoException("não encontrado"));

        assertThrows(RegistroNaoEncontradoException.class, () -> useCase.executar(id));
        verify(deletarVampiroPorId, never()).deletarById(any());
    }
}
