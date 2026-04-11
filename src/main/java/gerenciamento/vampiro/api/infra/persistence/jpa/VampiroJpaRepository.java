package gerenciamento.vampiro.api.infra.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VampiroJpaRepository extends JpaRepository<VampiroEntity, UUID> {
}
