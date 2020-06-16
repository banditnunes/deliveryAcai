package br.com.anderson.acaiDelivey.form;

import br.com.anderson.acaiDelivey.model.Complemento;
import br.com.anderson.acaiDelivey.model.Pedido;
import br.com.anderson.acaiDelivey.model.Produto;
import br.com.anderson.acaiDelivey.model.Sabor;
import br.com.anderson.acaiDelivey.repository.ComplementoRepository;
import br.com.anderson.acaiDelivey.repository.ProdutoRepository;
import br.com.anderson.acaiDelivey.repository.SaborRepository;

import javax.validation.constraints.NotNull;
import java.util.*;

public class PedidoForm {

    @NotNull
    private Long tamanho;
    @NotNull
    private Long sabor;

    public boolean validarSaborTamanho(SaborRepository saborRepository, ProdutoRepository produtoRepository, PedidoForm pedidoForm) {
        Optional<Sabor> saborOptional = saborRepository.findById(sabor);
        Optional<Produto> produtoOptional = produtoRepository.findById(tamanho);
        return saborOptional.isPresent() && produtoOptional.isPresent();

    }
    public Map<String,Optional> obterObjetoSabor(SaborRepository saborRepository,ProdutoRepository produtoRepository){
        Map<String,Optional> mapObjetos = new HashMap<>();
         mapObjetos.put("sabor",saborRepository.findById(sabor));
         mapObjetos.put("tamanho",produtoRepository.findById(tamanho));
         return mapObjetos;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public Long getSabor() {
        return sabor;
    }

    public void setSabor(Long sabor) {
        this.sabor = sabor;
    }

}
