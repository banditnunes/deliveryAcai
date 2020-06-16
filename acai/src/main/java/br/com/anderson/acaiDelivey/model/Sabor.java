package br.com.anderson.acaiDelivey.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "sabor")
public class Sabor  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Long tempoAdicional;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        Sabor sabor = (Sabor) o;
        return id.equals(sabor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
