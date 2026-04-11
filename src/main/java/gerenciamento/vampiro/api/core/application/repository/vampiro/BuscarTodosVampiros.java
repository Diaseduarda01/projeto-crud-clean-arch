package gerenciamento.vampiro.api.core.application.repository.vampiro;

import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;

import java.util.List;

public interface BuscarTodosVampiros {

    List<Vampiro> findAll();
}
