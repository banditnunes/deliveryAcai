package br.com.anderson.acaiDelivey.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


@Entity(name = "pedido")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Sabor sabor;
    private Long tempoPreparo;
    private Double valorTotal;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Complemento> complementos;

    public Pedido() {

    }

    public Pedido(Produto tamanho, Sabor sabor, Set<Complemento> complementos) {
        this.produto = tamanho;
        this.complementos = complementos;
        this.sabor = sabor;
        this.calcularTempoPedido();
        this.calcularValorTotalPedido();
    }

    public Pedido(Produto tamanho, Sabor sabor) {
        this.produto = tamanho;
        this.sabor = sabor;
        this.calcularTempoPedido();
        this.calcularValorTotalPedido();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Sabor getSabor() {
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    public Set<Complemento> getComplementos() {
        return complementos;
    }

    public void setComplementos(Set<Complemento> complementos) {
        this.complementos = complementos;
    }

    public Long getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(Long tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void calcularTempoPedido() {
        setTempoPreparo(getProduto().getTempo() + getSabor().getTempoAdicional());
    }

    public void calcularValorTotalPedido() {
        if (getComplementos() != null && !getComplementos().isEmpty()) {
            setValorTotal(getComplementos().stream().map(item -> item.getValor()).mapToDouble(Double::doubleValue).sum() + produto.getValor());
        } else {
            setValorTotal(produto.getValor());
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
