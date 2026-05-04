package gerenciamento.vampiro.api.core.application.usecase.vampiro.dto;

import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;

import java.util.UUID;

public class VampiroResponseDto {

    private UUID id;
    private String nome;
    private Integer idade;
    private String especie;
    private String status;
    private String cidade;
    private Boolean estaCompelido;

    public VampiroResponseDto() {
    }

    public VampiroResponseDto(UUID id, String nome, Integer idade, String especie, String status, String cidade, Boolean estaCompelido) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.status = status;
        this.cidade = cidade;
        this.estaCompelido = estaCompelido;
    }

    public static VampiroResponseDto toResponse(Vampiro vampiro){
        return new VampiroResponseDto(
                vampiro.getId(),
                vampiro.getNome(),
                vampiro.getIdade().getValue(),
                vampiro.getEspecie().getValue(),
                vampiro.getStatus().getValue(),
                vampiro.getCidadeAtual(),
                vampiro.getEstaCompelido()
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
