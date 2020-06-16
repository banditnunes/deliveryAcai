package br.com.anderson.acaiDelivey.controller;

import br.com.anderson.acaiDelivey.DTO.AtualizaPedidoDTO;
import br.com.anderson.acaiDelivey.DTO.DetalhePedidoDTO;
import br.com.anderson.acaiDelivey.DTO.PedidoDTO;
import br.com.anderson.acaiDelivey.form.PedidoForm;
import br.com.anderson.acaiDelivey.model.Pedido;
import br.com.anderson.acaiDelivey.service.PedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    @Transactional
    public ResponseEntity<PedidoDTO> cadastrar(@RequestHeader("Authorization") String token, @RequestBody @Valid PedidoForm pedidoForm, UriComponentsBuilder uriComponentsBuilder) {
        return pedidoService.cadastrar(pedidoForm, uriComponentsBuilder);

    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<AtualizaPedidoDTO> personalizar(@PathVariable Long id, @RequestBody AtualizaPedidoDTO atualizaPedidoDTO) {
        return pedidoService.personalizar(id, atualizaPedidoDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhePedidoDTO> detalharPedido(@PathVariable Long id) {
        return pedidoService.detalhar(id);
    }

}
