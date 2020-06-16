package br.com.anderson.acaiDelivey.DTO;

import br.com.anderson.acaiDelivey.model.Pedido;

import java.io.Serializable;

public class PedidoDTO implements Serializable {
    private String tamanho;
    private String sabor;
    private Double valorTotal;

    public PedidoDTO(Pedido pedido) {
        this.tamanho = pedido.getProduto().getTamanho();
        this.sabor = pedido.getSabor().getDescricao();
        this.valorTotal = pedido.getValorTotal();
    }


    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }


}
