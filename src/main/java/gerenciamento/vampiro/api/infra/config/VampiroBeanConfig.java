package gerenciamento.vampiro.api.infra.config;

import gerenciamento.vampiro.api.core.application.usecase.vampiro.*;
import gerenciamento.vampiro.api.infra.persistence.jpa.VampiroJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VampiroBeanConfig {

    @Bean
    public CadastrarUseCase cadastrarUseCase(VampiroJpaAdapter jpaAdapter){
        return new CadastrarUseCase(jpaAdapter);
    }

    @Bean
    public ListarTodosUseCase listarTodosUseCase(VampiroJpaAdapter jpaAdapter){
        return new ListarTodosUseCase(jpaAdapter);
    }

    @Bean
    public ListarPorIdUseCase listarPorIdUseCase(VampiroJpaAdapter jpaAdapter){
        return new ListarPorIdUseCase(jpaAdapter);
    }

    @Bean
    public AtualizarUseCase atualizarUseCase(VampiroJpaAdapter jpaAdapter){
        return new AtualizarUseCase(jpaAdapter, listarPorIdUseCase(jpaAdapter));
    }

    @Bean
    public DeletarUseCase deletarUseCase(VampiroJpaAdapter jpaAdapter){
        return new DeletarUseCase(jpaAdapter, listarPorIdUseCase(jpaAdapter));
    }
}
