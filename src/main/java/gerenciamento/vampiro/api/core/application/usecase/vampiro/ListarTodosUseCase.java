package gerenciamento.vampiro.api.core.application.usecase.vampiro;

import gerenciamento.vampiro.api.core.application.repository.vampiro.BuscarTodosVampiros;
import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;

import java.util.List;

public class ListarTodosUseCase {

    private final BuscarTodosVampiros buscarTodosVampiros;

    public ListarTodosUseCase(BuscarTodosVampiros buscarTodosVampiros) {
        this.buscarTodosVampiros = buscarTodosVampiros;
    }

    public List<Vampiro> executar() {
        return buscarTodosVampiros.findAll();
    }
}