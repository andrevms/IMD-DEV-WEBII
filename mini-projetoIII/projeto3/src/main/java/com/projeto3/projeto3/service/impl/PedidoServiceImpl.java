package com.projeto3.projeto3.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.projeto3.projeto3.enums.StatusPedido;
import com.projeto3.projeto3.exception.PedidoNaoEncontradoException;
import com.projeto3.projeto3.exception.RegraNegocioException;
import com.projeto3.projeto3.model.Cliente;
import com.projeto3.projeto3.model.Estoque;
import com.projeto3.projeto3.model.ItemPedido;
import com.projeto3.projeto3.model.Pedido;
import com.projeto3.projeto3.model.Produto;
import com.projeto3.projeto3.repository.ClienteRepository;
import com.projeto3.projeto3.repository.ItemPedidoRepository;
import com.projeto3.projeto3.repository.PedidoRepository;
import com.projeto3.projeto3.repository.ProdutoRepository;
import com.projeto3.projeto3.rest.dto.EstoqueDTO;
import com.projeto3.projeto3.rest.dto.ItemPedidoDTO;
import com.projeto3.projeto3.rest.dto.PedidoDTO;
import com.projeto3.projeto3.service.EstoqueService;
import com.projeto3.projeto3.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clientesRepository;
    private final ProdutoRepository produtosRepository;
    private final ItemPedidoRepository itemsPedidoRepository;
    private final EstoqueService estoqueService;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);


        BigDecimal total = new BigDecimal(0);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());

        for (ItemPedido itemPedido : itemsPedido) {
            Estoque estoqueAtual = estoqueService.obterEstoquePorProdutoId(itemPedido.getPedido().getId());

            if(estoqueAtual.getQuantidade() < itemPedido.getQuantidade()){
                throw new RegraNegocioException("Quantidade do Produto "+ itemPedido.getProduto().getDescricao() + " Insuficiente no Estoque");
            }else{
                EstoqueDTO estoqueDTO = new EstoqueDTO();
                estoqueDTO.setId(estoqueAtual.getId());
                int numAtual = estoqueAtual.getQuantidade();
                estoqueDTO.setQuantidade(numAtual - itemPedido.getQuantidade());
                estoqueService.atualizaEstoque(estoqueDTO);


                //atualiza preço total
                BigDecimal d = new BigDecimal(itemPedido.getQuantidade());
                total = total.add(itemPedido.getProduto().getPreco().multiply(d));
            }
        }

        pedido.setTotal(total);

        pedidoRepository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {

        return pedidoRepository.findByIdFetchItens(id);
    }

    @Override
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidoRepository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidoRepository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());

    }

    

    @Override
    public void deletar(Integer id) {
        pedidoRepository.findById(id)
        .map( pedido -> {
            pedidoRepository.delete(pedido);
            return pedido;
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Pedido não encontrado") );
        
    }

    @Override
    public void atualizaClientePedido(Integer id, Integer clienteId) {

        pedidoRepository
        .findById(id)
        .map(pedido -> {
            pedido.setCliente(
                clientesRepository.findById(clienteId)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado")));
            return pedidoRepository.save(pedido);
        }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    
}
