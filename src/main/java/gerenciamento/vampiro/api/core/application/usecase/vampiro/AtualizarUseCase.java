package gerenciamento.vampiro.api.core.application.usecase.vampiro;

import gerenciamento.vampiro.api.core.application.repository.vampiro.AtualizarVampiro;
import gerenciamento.vampiro.api.core.application.usecase.vampiro.dto.VampiroRequestDto;
import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;

import java.util.UUID;

public class AtualizarUseCase {

    private final AtualizarVampiro atualizarVampiro;
    private final ListarPorIdUseCase listarPorIdUseCase;

    public AtualizarUseCase(AtualizarVampiro atualizarVampiro, ListarPorIdUseCase listarPorIdUseCase) {
        this.atualizarVampiro = atualizarVampiro;
        this.listarPorIdUseCase = listarPorIdUseCase;
    }

    public Vampiro executar(UUID id, VampiroRequestDto dadosNovos) {
        Vampiro vampiroExistente = listarPorIdUseCase.executar(id);

        Vampiro vampiroAtualizado = Vampiro.atualizar(
                vampiroExistente.getId(),
                dadosNovos.getNome(),
                dadosNovos.getIdade(),
                dadosNovos.getEspecie(),
                dadosNovos.getStatus(),
                dadosNovos.getCidade(),
                dadosNovos.getEstaCompelido()
        );

        return atualizarVampiro.update(vampiroAtualizado);
    }
}
