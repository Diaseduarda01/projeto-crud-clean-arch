package gerenciamento.vampiro.api.core.domain.vampiro.valueObject;

import gerenciamento.vampiro.api.core.domain.vampiro.exception.IdadeInvalidaException;

public class Idade {

    private final Integer value;

    private Idade(Integer value) {
        this.value = value;
    }

    public static Idade build(Integer value){
        if (!(value > 0)) {
            throw new IdadeInvalidaException("Idade de vampiro inválida!");
        }

        return new Idade(value);
    }

    public Integer getValue() {
        return value;
    }
}
