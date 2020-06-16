package br.com.anderson.acaiDelivey.service;

import br.com.anderson.acaiDelivey.DTO.AtualizaPedidoDTO;
import br.com.anderson.acaiDelivey.DTO.DetalhePedidoDTO;
import br.com.anderson.acaiDelivey.DTO.PedidoDTO;
import br.com.anderson.acaiDelivey.form.PedidoForm;
import br.com.anderson.acaiDelivey.model.*;
import br.com.anderson.acaiDelivey.repository.ComplementoRepository;
import br.com.anderson.acaiDelivey.repository.PedidoRepository;
import br.com.anderson.acaiDelivey.repository.ProdutoRepository;
import br.com.anderson.acaiDelivey.repository.SaborRepository;
import br.com.anderson.acaiDelivey.util.CustomResponseEntity;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ComplementoRepository complementoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    SaborRepository saborRepository;

    public ResponseEntity<PedidoDTO> cadastrar(PedidoForm pedidoForm, UriComponentsBuilder uriComponentsBuilder) {
        try {
            Pedido pedido = validarSaborTamanhoEnviados(pedidoForm);
            pedidoRepository.save(pedido);
            URI uri = uriComponentsBuilder.path("/pedido/{id}").buildAndExpand(pedido.getId()).toUri();
            return ResponseEntity.created(uri).body(new PedidoDTO(pedido));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<AtualizaPedidoDTO> personalizar(Long id, AtualizaPedidoDTO atualizaPedidoDTO) {
        Optional<Pedido> pedidoRetornado = pedidoRepository.findById(id);
        if (pedidoRetornado.isPresent()) {
            atualizarPedido(pedidoRetornado.get(), atualizaPedidoDTO);
            return new CustomResponseEntity<AtualizaPedidoDTO>(atualizaPedidoDTO, HttpStatus.OK);

        }
        return new CustomResponseEntity<AtualizaPedidoDTO>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<DetalhePedidoDTO> detalhar(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            DetalhePedidoDTO detalhePedidoDTO = new DetalhePedidoDTO(pedido.get());
            return new CustomResponseEntity<DetalhePedidoDTO>(detalhePedidoDTO, HttpStatus.OK);
        } else {
            return new CustomResponseEntity<DetalhePedidoDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    private void atualizarPedido(Pedido pedido, AtualizaPedidoDTO atualizaPedidoDTO) {
        if (atualizaPedidoDTO.getComplementos() != null && !atualizaPedidoDTO.getComplementos().isEmpty()) {
            processarComplementos(pedido, atualizaPedidoDTO);
        }

    }

    private void processarComplementos(Pedido pedido, AtualizaPedidoDTO atualizaPedidoDTO) {
        atualizaPedidoDTO.getComplementos().stream().forEach(complemento -> {
            Complemento complementoRetornado = obterComplementoRetornado(complemento);
            atualizarComplementosPedido(pedido, complementoRetornado);
        });
    }

    private void atualizarComplementosPedido(Pedido pedido, Complemento complementoRetornado) {
        if (pedido!=null && complementoRetornado!=null && complementoRetornado.getDescricao()!=null && !pedido.getComplementos().contains(complementoRetornado)) {

            pedido.setValorTotal(pedido.getValorTotal() + complementoRetornado.getValor());
            pedido.setTempoPreparo(pedido.getTempoPreparo() + complementoRetornado.getTempoAdicional());
            pedido.getComplementos().add(complementoRetornado);
        }
    }

    private Complemento obterComplementoRetornado(String complemento) {

        return complementoRepository.findByDescricao(complemento).stream().findFirst().orElse(new Complemento());
    }

    private Pedido validarSaborTamanhoEnviados(PedidoForm pedidoForm) throws NotFoundException {
        if (pedidoForm.validarSaborTamanho(saborRepository, produtoRepository,pedidoForm)) {
            Map<String,Optional> optionalStringMap = pedidoForm.obterObjetoSabor(saborRepository, produtoRepository);
            Sabor saborRetornado = (Sabor) optionalStringMap.get("sabor").orElse(new Sabor());
            Produto tamanhoRetornado = (Produto) optionalStringMap.get("tamanho").orElse(new Produto());
            return new Pedido(tamanhoRetornado, saborRetornado);
        } else {
            throw new NotFoundException("Sabor ou Tamanho não encontrados");
        }
    }
}
