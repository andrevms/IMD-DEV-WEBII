package com.miniprojeto2.miniprojeto2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.miniprojeto2.miniprojeto2.model.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, String>{
    @Query(value = " select * from avatar c where c.estudante_id = estudante_id",nativeQuery = true)
    List<Avatar> encontrarPorEstudanteId(@Param("estudante_id") int estudante_id);
}
