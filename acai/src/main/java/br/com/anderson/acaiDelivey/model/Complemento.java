package br.com.anderson.acaiDelivey.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "complemento")
public class Complemento implements Serializable {

    @Id
    private Long id;
    private String descricao;
    private Double valor;
    private Long tempoAdicional;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getTempoAdicional() {
        return tempoAdicional;
    }

    public void setTempoAdicional(Long tempoAdicional) {
        this.tempoAdicional = tempoAdicional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complemento that = (Complemento) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
