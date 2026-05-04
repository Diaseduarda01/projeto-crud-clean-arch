package gerenciamento.vampiro.api.infra.web.rest;

import gerenciamento.vampiro.api.core.application.usecase.vampiro.dto.VampiroRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
class VampiroControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void deveCadastrarVampiroERetornar201() throws Exception {
        VampiroRequestDto dto = buildDto("Drácula", 500, "VAMPIRO", "ATIVO", "Transilvânia", false);

        mockMvc.perform(post("/vampiros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nome").value("Drácula"))
                .andExpect(jsonPath("$.idade").value(500))
                .andExpect(jsonPath("$.especie").value("VAMPIRO"))
                .andExpect(jsonPath("$.status").value("ATIVO"));
    }

    @Test
    void deveRetornar400QuandoCamposObrigatoriosFaltando() throws Exception {
        mockMvc.perform(post("/vampiros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornar400QuandoEspecieInvalida() throws Exception {
        VampiroRequestDto dto = buildDto("Test", 100, "HUMANO", "ATIVO", "SP", false);

        mockMvc.perform(post("/vampiros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornar400QuandoStatusInvalido() throws Exception {
        VampiroRequestDto dto = buildDto("Test", 100, "VAMPIRO", "INATIVO", "SP", false);

        mockMvc.perform(post("/vampiros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveListarTodosOsVampiros() throws Exception {
        cadastrar(buildDto("Lestat", 300, "ORIGINAL", "ATIVO", "Paris", true));
        cadastrar(buildDto("Klaus", 1000, "HIBRIDO", "ATIVO", "Mystic Falls", false));

        mockMvc.perform(get("/vampiros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void deveRetornar204QuandoListaVazia() throws Exception {
        mockMvc.perform(get("/vampiros"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveBuscarVampiroPorId() throws Exception {
        String id = cadastrar(buildDto("Klaus", 1000, "HIBRIDO", "ATIVO", "Mystic Falls", false));

        mockMvc.perform(get("/vampiros/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("Klaus"));
    }

    @Test
    void deveRetornar404QuandoVampiroNaoEncontrado() throws Exception {
        mockMvc.perform(get("/vampiros/{id}", UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveAtualizarVampiro() throws Exception {
        String id = cadastrar(buildDto("Spike", 100, "VAMPIRO", "ATIVO", "Sunnydale", false));
        VampiroRequestDto update = buildDto("Spike Atualizado", 101, "VAMPIRO", "ADORMECIDO", "London", true);

        mockMvc.perform(put("/vampiros/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Spike Atualizado"))
                .andExpect(jsonPath("$.status").value("ADORMECIDO"))
                .andExpect(jsonPath("$.estaCompelido").value(true));
    }

    @Test
    void deveRetornar404AoAtualizarVampiroInexistente() throws Exception {
        VampiroRequestDto dto = buildDto("X", 100, "VAMPIRO", "ATIVO", "SP", false);

        mockMvc.perform(put("/vampiros/{id}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveDeletarVampiro() throws Exception {
        String id = cadastrar(buildDto("Angel", 200, "VAMPIRO", "ATIVO", "Los Angeles", false));

        mockMvc.perform(delete("/vampiros/{id}", id))
                .andExpect(status().isOk());

        mockMvc.perform(get("/vampiros/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornar404AoDeletarVampiroInexistente() throws Exception {
        mockMvc.perform(delete("/vampiros/{id}", UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    private String cadastrar(VampiroRequestDto dto) throws Exception {
        MvcResult result = mockMvc.perform(post("/vampiros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto)))
                .andReturn();
        return objectMapper.readTree(result.getResponse().getContentAsByteArray()).get("id").asText();
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
