package com.phillrocha.forum.repository;

import com.phillrocha.forum.models.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findByTitulo(String titulo, Pageable pagina);

    Page<Topico> findByCursoNome(String nomeCurso, Pageable pagina);


}
