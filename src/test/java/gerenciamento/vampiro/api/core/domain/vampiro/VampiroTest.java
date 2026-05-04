package gerenciamento.vampiro.api.core.domain.vampiro;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VampiroTest {

    @Test
    void deveConstruirVampiroNovo() {
        Vampiro vampiro = Vampiro.build("Drácula", 500, "VAMPIRO", "ATIVO", "Transilvânia", false);

        assertNotNull(vampiro.getId());
        assertEquals("Drácula", vampiro.getNome());
        assertEquals(500, vampiro.getIdade().getValue());
        assertEquals("VAMPIRO", vampiro.getEspecie().getValue());
        assertEquals("ATIVO", vampiro.getStatus().getValue());
        assertEquals("Transilvânia", vampiro.getCidadeAtual());
        assertFalse(vampiro.getEstaCompelido());
    }

    @Test
    void deveConstruirVampiroExistente() {
        String id = "550e8400-e29b-41d4-a716-446655440000";
        Vampiro vampiro = Vampiro.existente(id, "Lestat", 300, "ORIGINAL", "ATIVO", "Paris", true);

        assertEquals(UUID.fromString(id), vampiro.getId());
        assertEquals("Lestat", vampiro.getNome());
        assertTrue(vampiro.getEstaCompelido());
    }

    @Test
    void deveConstruirVampiroAtualizado() {
        Vampiro original = Vampiro.build("Klaus", 1000, "HIBRIDO", "ATIVO", "Mystic Falls", false);
        Vampiro atualizado = Vampiro.atualizar(original.getId(), "Klaus Mikaelson", 1000, "HIBRIDO", "ADORMECIDO", "Nova Orleans", false);

        assertEquals(original.getId(), atualizado.getId());
        assertEquals("Klaus Mikaelson", atualizado.getNome());
        assertEquals("ADORMECIDO", atualizado.getStatus().getValue());
        assertEquals("Nova Orleans", atualizado.getCidadeAtual());
    }

    @Test
    void deveCadaVampiroNovoTerIdUnico() {
        Vampiro v1 = Vampiro.build("Drácula", 500, "VAMPIRO", "ATIVO", "Transilvânia", false);
        Vampiro v2 = Vampiro.build("Lestat", 300, "ORIGINAL", "ATIVO", "Paris", false);

        assertNotEquals(v1.getId(), v2.getId());
    }
}
