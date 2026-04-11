package gerenciamento.vampiro.api.core.application.usecase.vampiro;

import gerenciamento.vampiro.api.core.application.repository.vampiro.CadastrarVampiro;
import gerenciamento.vampiro.api.core.application.usecase.vampiro.dto.VampiroRequestDto;
import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;

public class CadastrarUseCase {

    private final CadastrarVampiro cadastrarVampiro;

    public CadastrarUseCase(CadastrarVampiro cadastrarVampiro) {
        this.cadastrarVampiro = cadastrarVampiro;
    }

    public Vampiro executar(VampiroRequestDto dto) {

        Vampiro vampiro = Vampiro.build(
                dto.getNome(),
                dto.getIdade(),
                dto.getEspecie(),
                dto.getStatus(),
                dto.getCidade(),
                dto.getEstaCompelido()
        );

        return cadastrarVampiro.save(vampiro);
    }
}
