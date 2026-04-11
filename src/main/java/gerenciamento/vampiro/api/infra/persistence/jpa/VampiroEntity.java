package gerenciamento.vampiro.api.infra.persistence.jpa;

import gerenciamento.vampiro.api.core.domain.vampiro.valueObject.Especie;
import gerenciamento.vampiro.api.core.domain.vampiro.valueObject.Idade;
import gerenciamento.vampiro.api.core.domain.vampiro.valueObject.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "vampiro")
public class VampiroEntity {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    private String nome;
    private Integer idade;
    private String especie;
    private String status;
    private String cidadeAtual;
    private Boolean estaCompelido;

    public VampiroEntity() {
    }

    public VampiroEntity(UUID id, String nome, Integer idade, String especie, String status, String cidadeAtual, Boolean estaCompelido) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.status = status;
        this.cidadeAtual = cidadeAtual;
        this.estaCompelido = estaCompelido;
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

    public String getCidadeAtual() {
        return cidadeAtual;
    }

    public void setCidadeAtual(String cidadeAtual) {
        this.cidadeAtual = cidadeAtual;
    }

    public Boolean getEstaCompelido() {
        return estaCompelido;
    }

    public void setEstaCompelido(Boolean estaCompelido) {
        this.estaCompelido = estaCompelido;
    }
}
