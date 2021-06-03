package com.phillrocha.forum.repository;

import com.phillrocha.forum.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface TopicoRepository extends JpaRepository<Topico, Long> {

}
