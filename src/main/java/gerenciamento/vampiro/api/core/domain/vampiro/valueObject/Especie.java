package gerenciamento.vampiro.api.core.domain.vampiro.valueObject;

import gerenciamento.vampiro.api.core.domain.vampiro.exception.EspecieVampiroInvalidaException;

import java.util.List;

public class Especie {

    private final String value;

    private Especie(String value) {
        this.value = value;
    }

    public static Especie build(String value) {
        List<String> correto = List.of("VAMPIRO", "HIBRIDO", "ORIGINAL");

        if (!correto.stream().anyMatch(value::equalsIgnoreCase)) {
            throw new EspecieVampiroInvalidaException("Espécie: %s inválida!".formatted(value));
        }

        return new Especie(value);
    }

    public String getValue() {
        return value;
    }
}