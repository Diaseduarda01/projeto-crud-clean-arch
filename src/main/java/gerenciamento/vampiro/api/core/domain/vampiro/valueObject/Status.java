package gerenciamento.vampiro.api.core.domain.vampiro.valueObject;

import gerenciamento.vampiro.api.core.domain.vampiro.exception.StatusInvalidoException;

import java.util.List;

public class Status {

    private final String value;

    private Status(String value) {
        this.value = value;
    }

    public static Status build(String value){
        List<String> correto = List.of("ATIVO", "ADORMECIDO", "MORTO");

        if (!correto.stream().anyMatch(value::equalsIgnoreCase)) {
            throw new StatusInvalidoException("Status de vampiro inválida!");
        }

        return new Status(value);
    }

    public String getValue() {
        return value;
    }
}
