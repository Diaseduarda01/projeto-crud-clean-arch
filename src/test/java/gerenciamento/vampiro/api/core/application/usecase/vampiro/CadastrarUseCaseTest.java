package gerenciamento.vampiro.api.core.application.usecase.vampiro;

import gerenciamento.vampiro.api.core.application.repository.vampiro.CadastrarVampiro;
import gerenciamento.vampiro.api.core.application.usecase.vampiro.dto.VampiroRequestDto;
import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastrarUseCaseTest {

    @Mock
    private CadastrarVampiro cadastrarVampiro;

    @InjectMocks
    private CadastrarUseCase useCase;

    @Test
    void deveCadastrarVampiroComSucesso() {
        VampiroRequestDto dto = buildDto("Drácula", 500, "VAMPIRO", "ATIVO", "Transilvânia", false);
        Vampiro vampiroSalvo = Vampiro.build("Drácula", 500, "VAMPIRO", "ATIVO", "Transilvânia", false);

        when(cadastrarVampiro.save(any(Vampiro.class))).thenReturn(vampiroSalvo);

        Vampiro resultado = useCase.executar(dto);

        assertNotNull(resultado);
        assertEquals("Drácula", resultado.getNome());
        assertEquals(500, resultado.getIdade().getValue());
        assertEquals("VAMPIRO", resultado.getEspecie().getValue());
        verify(cadastrarVampiro, times(1)).save(any(Vampiro.class));
    }

    @Test
    void deveChamarRepositorioSave() {
        VampiroRequestDto dto = buildDto("Lestat", 300, "ORIGINAL", "ATIVO", "Paris", true);
        when(cadastrarVampiro.save(any(Vampiro.class))).thenAnswer(inv -> inv.getArgument(0));

        useCase.executar(dto);

        verify(cadastrarVampiro).save(any(Vampiro.class));
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
