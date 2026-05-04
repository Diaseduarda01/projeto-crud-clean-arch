package gerenciamento.vampiro.api.core.application.usecase.vampiro;

import gerenciamento.vampiro.api.core.application.repository.vampiro.BuscarTodosVampiros;
import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarTodosUseCaseTest {

    @Mock
    private BuscarTodosVampiros buscarTodosVampiros;

    @InjectMocks
    private ListarTodosUseCase useCase;

    @Test
    void deveRetornarListaComVampiros() {
        List<Vampiro> vampiros = List.of(
                Vampiro.build("Drácula", 500, "VAMPIRO", "ATIVO", "Transilvânia", false),
                Vampiro.build("Lestat", 300, "ORIGINAL", "ATIVO", "Paris", true)
        );
        when(buscarTodosVampiros.findAll()).thenReturn(vampiros);

        List<Vampiro> resultado = useCase.executar();

        assertEquals(2, resultado.size());
        verify(buscarTodosVampiros, times(1)).findAll();
    }

    @Test
    void deveRetornarListaVazia() {
        when(buscarTodosVampiros.findAll()).thenReturn(List.of());

        List<Vampiro> resultado = useCase.executar();

        assertTrue(resultado.isEmpty());
    }
}
