package gerenciamento.vampiro.api.core.application.usecase.vampiro;

import gerenciamento.vampiro.api.core.application.repository.vampiro.AtualizarVampiro;
import gerenciamento.vampiro.api.core.application.usecase.shared.RegistroNaoEncontradoException;
import gerenciamento.vampiro.api.core.application.usecase.vampiro.dto.VampiroRequestDto;
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
class AtualizarUseCaseTest {

    @Mock
    private AtualizarVampiro atualizarVampiro;

    @Mock
    private ListarPorIdUseCase listarPorIdUseCase;

    @InjectMocks
    private AtualizarUseCase useCase;

    @Test
    void deveAtualizarVampiroComSucesso() {
        UUID id = UUID.randomUUID();
        Vampiro vampiroExistente = Vampiro.build("Drácula", 500, "VAMPIRO", "ATIVO", "Transilvânia", false);
        VampiroRequestDto dto = buildDto("Drácula Atualizado", 600, "ORIGINAL", "ADORMECIDO", "Londres", true);

        when(listarPorIdUseCase.executar(id)).thenReturn(vampiroExistente);
        when(atualizarVampiro.update(any(Vampiro.class))).thenAnswer(inv -> inv.getArgument(0));

        Vampiro resultado = useCase.executar(id, dto);

        assertEquals("Drácula Atualizado", resultado.getNome());
        assertEquals(600, resultado.getIdade().getValue());
        assertEquals("ORIGINAL", resultado.getEspecie().getValue());
        assertEquals("ADORMECIDO", resultado.getStatus().getValue());
        verify(atualizarVampiro, times(1)).update(any(Vampiro.class));
    }

    @Test
    void deveLancarExcecaoQuandoVampiroNaoEncontrado() {
        UUID id = UUID.randomUUID();
        VampiroRequestDto dto = buildDto("X", 100, "VAMPIRO", "ATIVO", "SP", false);
        when(listarPorIdUseCase.executar(id)).thenThrow(new RegistroNaoEncontradoException("não encontrado"));

        assertThrows(RegistroNaoEncontradoException.class, () -> useCase.executar(id, dto));
        verify(atualizarVampiro, never()).update(any());
    }

    private VampiroRequestDto buildDto(String nome, int idade, String especie, String status, String cidade, boolean compelido) {
        VampiroRequestDto dto = new VampiroRequestDto();
        dto.setNome(nome);
        dto.setIdade(idade);
        dto.setEspecie(especie);
        dto.setStatus(status);
        dto.setCidade(cidade);
        dto.setEstaCompelido(compelido);
        return dto;
    }
}
