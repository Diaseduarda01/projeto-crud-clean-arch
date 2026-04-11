package gerenciamento.vampiro.api.core.application.usecase.vampiro.dto;

public class VampiroRequestDto {

    private String nome;
    private Integer idade;
    private String especie;
    private String status;
    private String cidade;
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
