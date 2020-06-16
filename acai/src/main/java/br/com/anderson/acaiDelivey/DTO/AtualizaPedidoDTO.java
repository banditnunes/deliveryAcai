package br.com.anderson.acaiDelivey.DTO;

import java.io.Serializable;
import java.util.Set;

public class AtualizaPedidoDTO implements Serializable {
    private Set<String> complementos;

    public Set<String> getComplementos() {
        return complementos;
    }

    public void setComplementos(Set<String> complementos) {
        this.complementos = complementos;
    }
}
