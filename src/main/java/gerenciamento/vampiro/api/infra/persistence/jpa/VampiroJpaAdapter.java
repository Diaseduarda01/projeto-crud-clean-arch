package gerenciamento.vampiro.api.infra.persistence.jpa;

import gerenciamento.vampiro.api.core.application.repository.vampiro.*;
import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class VampiroJpaAdapter implements AtualizarVampiro, DeletarVampiroPorId,
        BuscarTodosVampiros, BuscarVampiroPorId, CadastrarVampiro {

    private final VampiroJpaRepository jpaRepository;

    public VampiroJpaAdapter(VampiroJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Vampiro update(Vampiro dadosNovos) {
        VampiroEntity entity = VampiroMapper.toEntity(dadosNovos);
        VampiroEntity salvo = jpaRepository.save(entity);
        return VampiroMapper.toDomain(salvo);
    }

    @Override
    public List<Vampiro> findAll() {
        return jpaRepository.findAll().stream().map(VampiroMapper::toDomain).toList();
    }

    @Override
    public Optional<Vampiro> findById(UUID id) {
        return jpaRepository.findById(id).map(VampiroMapper::toDomain);
    }

    @Override
    public Vampiro save(Vampiro vampiro) {
        VampiroEntity salvar = VampiroMapper.toEntity(vampiro);
        VampiroEntity salvo = jpaRepository.save(salvar);
        return VampiroMapper.toDomain(salvo);
    }

    @Override
    public void deletarById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
