package com.projeto3.projeto3.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projeto3.projeto3.model.Cliente;
import com.projeto3.projeto3.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
    List<Pedido> findByCliente(Cliente cliente);
    
    //Optional<Pedido> findByIdFetchItens(Integer id);
    @Query(" select p from Pedido p left join fetch p.itens where p.id = :id ")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
    
}
