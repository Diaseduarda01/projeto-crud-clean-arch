package gerenciamento.vampiro.api.core.application.usecase.vampiro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class VampiroRequestDto {

    @NotBlank(message = "nome é obrigatório")
    private String nome;

    @NotNull(message = "idade é obrigatória")
    @Positive(message = "idade deve ser maior que zero")
    private Integer idade;

    @NotBlank(message = "especie é obrigatória")
    private String especie;

    @NotBlank(message = "status é obrigatório")
    private String status;

    @NotBlank(message = "cidade é obrigatória")
    private String cidade;

    @NotNull(message = "estaCompelido é obrigatório")
    private Boolean estaCompelido;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Boolean getEstaCompelido() {
        return estaCompelido;
    }

    public void setEstaCompelido(Boolean estaCompelido) {
        this.estaCompelido = estaCompelido;
    }
}
