package gerenciamento.vampiro.api.core.application.usecase.vampiro;

import gerenciamento.vampiro.api.core.application.repository.vampiro.DeletarVampiroPorId;
import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;

import java.util.UUID;

public class DeletarUseCase {

    private final DeletarVampiroPorId deletarVampiroPorId;
    private final ListarPorIdUseCase listarPorIdUseCase;

     public DeletarUseCase(DeletarVampiroPorId deletarVampiroPorId, ListarPorIdUseCase listarPorIdUseCase) {
        this.deletarVampiroPorId = deletarVampiroPorId;
        this.listarPorIdUseCase = listarPorIdUseCase;
    }

    public String executar(UUID id) {
        Vampiro vampiroExistente = listarPorIdUseCase.executar(id);
        deletarVampiroPorId.delatarById(vampiroExistente.getId());

        return ("Vampiro com id: %s deletado com sucesso!").formatted(vampiroExistente.getId());
    }
}
