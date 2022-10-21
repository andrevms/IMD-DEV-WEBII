package com.projeto3.projeto3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto3.projeto3.model.Cliente;
import com.projeto3.projeto3.repository.ClienteRepository;
import com.projeto3.projeto3.rest.dto.ClientePedidoDTO;
import com.projeto3.projeto3.rest.dto.InformacaoItemPedidoDTO;
import com.projeto3.projeto3.rest.dto.PedidoClienteDTO;
import com.projeto3.projeto3.service.ClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository repository;

    public ClientePedidoDTO obterListPedidosDoCliente(Integer id) {

        Cliente c = repository.findClienteFetchPedidos(id);
        ClientePedidoDTO cDTO = new ClientePedidoDTO();
        cDTO.setCpf(c.getCpf());
        cDTO.setNome(c.getNome());

        List<PedidoClienteDTO> listPedidos = new ArrayList<>();

        for (int i = 0; i < c.getPedidos().size(); i++) {
            PedidoClienteDTO pCLientDTO = new PedidoClienteDTO();
            pCLientDTO.setId(c.getPedidos().get(i).getId());
            pCLientDTO.setTotal(c.getPedidos().get(i).getTotal());

            List<InformacaoItemPedidoDTO> listItens = new ArrayList<>();
            int sizeListItens = c.getPedidos().get(i).getItens().size();
            System.out.println(sizeListItens + " \n");
            for (int j = 0; j < sizeListItens; j++) {

                InformacaoItemPedidoDTO item = new InformacaoItemPedidoDTO();
                item.setDescricaoProduto(c.getPedidos().get(i).getItens().get(j).getProduto().getDescricao());
                item.setQuantidade(c.getPedidos().get(i).getItens().get(j).getQuantidade());
                item.setPrecoUnitario(c.getPedidos().get(i).getItens().get(j).getProduto().getPreco());

                listItens.add(item);
            }
            pCLientDTO.setItens(listItens);
            listPedidos.add(pCLientDTO);
        }

        cDTO.setPedidos(listPedidos);
        return cDTO;
    }

}
