package br.com.anderson.acaiDelivey.DTO;

import br.com.anderson.acaiDelivey.model.Complemento;
import br.com.anderson.acaiDelivey.model.Pedido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DetalhePedidoDTO implements Serializable {

    private Long id;
    private String tamanho;
    private Double valorPorTamanho;
    private String sabor;
    private List<ComplementoDTO> personalizacao = new ArrayList<>();
    private Double totalPedido;
    private Long tempoPreparo;

    public DetalhePedidoDTO() {
    }

    public DetalhePedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.sabor = pedido.getSabor().getDescricao();
        this.tamanho = pedido.getProduto().getTamanho();
        this.valorPorTamanho = pedido.getProduto().getValor();
        this.tempoPreparo = pedido.getTempoPreparo();
        this.totalPedido = pedido.getValorTotal();

        configurarPersonalizacao(pedido.getComplementos());

    }

    private void configurarPersonalizacao(Set<Complemento> complementos) {
        if (complementos.size() > 0)
            complementos.stream().forEach(o -> {

                this.personalizacao.add(new ComplementoDTO(o.getDescricao(), o.getValor()));
            });

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(Long tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public Double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Double getValorPorTamanho() {
        return valorPorTamanho;
    }

    public void setValorPorTamanho(Double valorPorTamanho) {
        this.valorPorTamanho = valorPorTamanho;
    }

    public List<ComplementoDTO> getPersonalizacao() {
        return personalizacao;
    }

    public void setPersonalizacao(List<ComplementoDTO> personalizacao) {
        this.personalizacao = personalizacao;
    }
}
