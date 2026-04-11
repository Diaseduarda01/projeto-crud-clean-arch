package gerenciamento.vampiro.api.core.application.repository.vampiro;

import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;

import java.util.Optional;
import java.util.UUID;

public interface BuscarVampiroPorId {

    Optional<Vampiro> findById(UUID id);
}
