package gerenciamento.vampiro.api.infra.persistence.jpa;

import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;

public class VampiroMapper {

    public VampiroMapper() {
    }

    public static VampiroEntity toEntity(Vampiro vampiro) {
        return new VampiroEntity(
                vampiro.getId(),
                vampiro.getNome(),
                vampiro.getIdade().getValue(),
                vampiro.getEspecie().getValue(),
                vampiro.getStatus().getValue(),
                vampiro.getCidadeAtual(),
                vampiro.getEstaCompelido()
        );
    }

    public static Vampiro toDomain(VampiroEntity vampiro) {
        return Vampiro.existente(
                vampiro.getId().toString(),
                vampiro.getNome(),
                vampiro.getIdade(),
                vampiro.getEspecie(),
                vampiro.getStatus(),
                vampiro.getCidadeAtual(),
                vampiro.getEstaCompelido()
        );
    }
}
