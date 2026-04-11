package gerenciamento.vampiro.api.core.domain.vampiro;

import gerenciamento.vampiro.api.core.domain.vampiro.valueObject.Especie;
import gerenciamento.vampiro.api.core.domain.vampiro.valueObject.Idade;
import gerenciamento.vampiro.api.core.domain.vampiro.valueObject.Status;

import java.util.UUID;

public class Vampiro {

    private UUID id;
    private String nome;
    private Idade idade;
    private Especie especie;
    private Status status;
    private String cidadeAtual;
    private Boolean estaCompelido;

    public Vampiro(UUID id, String nome, Idade idade, Especie especie,
                   Status status, String cidadeAtual, Boolean estaCompelido) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
        this.status = status;
        this.cidadeAtual = cidadeAtual;
        this.estaCompelido = estaCompelido;
    }

    public static Vampiro build(String nome, Integer idade,
                                String especie, String status,
                                String cidade, Boolean compelido){

        UUID id = UUID.randomUUID();
        Especie especievalida = Especie.build(especie);
        Status statusValido = Status.build(status);
        Idade idadeValida = Idade.build(idade);

        return new Vampiro(id,nome,idadeValida,especievalida,statusValido,cidade,compelido);
    }

    public static Vampiro existente(String id, String nome, Integer idade,
                                    String especie, String status,
                                    String cidade, Boolean compelido){
        return new Vampiro(
                UUID.fromString(id),
                nome,
                Idade.build(idade),
                Especie.build(especie),
                Status.build(status),
                cidade,
                compelido);
    }

    public static Vampiro atualizar(UUID id, String nome, Integer idade,
                                    String especie, String status,
                                    String cidade, Boolean compelido){
        Especie especieValida = Especie.build(especie);
        Status statusValido = Status.build(status);
        Idade idadeValida = Idade.build(idade);

        return new Vampiro(id, nome, idadeValida, especieValida, statusValido, cidade, compelido);
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Idade getIdade() {
        return idade;
    }

    public Especie getEspecie() {
        return especie;
    }

    public Status getStatus() {
        return status;
    }

    public String getCidadeAtual() {
        return cidadeAtual;
    }

    public Boolean getEstaCompelido() {
        return estaCompelido;
    }
}
