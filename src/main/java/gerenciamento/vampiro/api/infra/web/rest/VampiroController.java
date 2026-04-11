package gerenciamento.vampiro.api.infra.web.rest;

import gerenciamento.vampiro.api.core.application.usecase.vampiro.*;
import gerenciamento.vampiro.api.core.application.usecase.vampiro.dto.VampiroRequestDto;
import gerenciamento.vampiro.api.core.application.usecase.vampiro.dto.VampiroResponseDto;
import gerenciamento.vampiro.api.core.domain.vampiro.Vampiro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vampiros")
public class VampiroController {

    private final CadastrarUseCase cadastrarUseCase;
    private final AtualizarUseCase atualizarUseCase;
    private final DeletarUseCase deletarUseCase;
    private final ListarPorIdUseCase listarPorIdUseCase;
    private final ListarTodosUseCase listarTodosUseCase;

    public VampiroController(CadastrarUseCase cadastrarUseCase, AtualizarUseCase atualizarUseCase,
                             DeletarUseCase deletarUseCase, ListarPorIdUseCase listarPorIdUseCase,
                             ListarTodosUseCase listarTodosUseCase) {
        this.cadastrarUseCase = cadastrarUseCase;
        this.atualizarUseCase = atualizarUseCase;
        this.deletarUseCase = deletarUseCase;
        this.listarPorIdUseCase = listarPorIdUseCase;
        this.listarTodosUseCase = listarTodosUseCase;
    }

    @PostMapping
    public ResponseEntity<VampiroResponseDto> salvar(@RequestBody VampiroRequestDto dto) {
        Vampiro vampiro = cadastrarUseCase.executar(dto);
        return ResponseEntity.status(201).body(VampiroResponseDto.toResponse(vampiro));
    }

    @GetMapping
    public ResponseEntity<List<VampiroResponseDto>> listar() {
        List<Vampiro> list = listarTodosUseCase.executar();

        return list.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(list.stream()
                .map(VampiroResponseDto::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VampiroResponseDto> listarPorId(@PathVariable UUID id) {
        Vampiro vampiro = listarPorIdUseCase.executar(id);
        return ResponseEntity.status(200).body(VampiroResponseDto.toResponse(vampiro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPorId(@PathVariable UUID id) {
        deletarUseCase.executar(id);
        return ResponseEntity.status(200).body("Vampiro deletado com sucesso!");
    }


    @PutMapping("/{id}")
    public ResponseEntity<VampiroResponseDto> atualizar(@PathVariable UUID id, @RequestBody VampiroRequestDto dto) {
        Vampiro vampiro = atualizarUseCase.executar(id, dto);
        return ResponseEntity.status(200).body(VampiroResponseDto.toResponse(vampiro));
    }
}
