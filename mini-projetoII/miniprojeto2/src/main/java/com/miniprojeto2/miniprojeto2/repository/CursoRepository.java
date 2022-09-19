package com.miniprojeto2.miniprojeto2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.miniprojeto2.miniprojeto2.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{
    
}
