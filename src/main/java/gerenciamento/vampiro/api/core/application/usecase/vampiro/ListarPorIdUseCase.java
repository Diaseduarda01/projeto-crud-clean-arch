package gerenciamento.vampiro.api.core.application.usecase.vampiro;

import gerenciamento.vampiro.api.core.application.repository.vampiro.BuscarVampiroPorId;
import gerenciamento.vampiro.api.core.application.usecase.shared.RegistroNaoEncontradoException;
import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;

import java.util.UUID;

public class ListarPorIdUseCase {

    private final BuscarVampiroPorId buscarVampiroPorId;

    public ListarPorIdUseCase(BuscarVampiroPorId buscarVampiroPorId) {
        this.buscarVampiroPorId = buscarVampiroPorId;
    }

    public Vampiro executar(UUID id){
        return buscarVampiroPorId.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(
                "Vampiro com id: %s não encontrado".formatted(id)));
    }
}
