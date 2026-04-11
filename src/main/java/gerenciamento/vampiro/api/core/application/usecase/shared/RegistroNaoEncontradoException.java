package gerenciamento.vampiro.api.core.application.usecase.shared;

public class RegistroNaoEncontradoException extends RuntimeException {
    public RegistroNaoEncontradoException(String message) {
        super(message);
    }
}
