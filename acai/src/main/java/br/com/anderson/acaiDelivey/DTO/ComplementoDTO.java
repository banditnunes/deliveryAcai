package br.com.anderson.acaiDelivey.DTO;


import java.io.Serializable;

public class ComplementoDTO implements Serializable {

    private String item;
    private Double valor;

    public ComplementoDTO() {

    }

    public ComplementoDTO(String item, Double valor) {
        this.item = item;
        this.valor = valor;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
