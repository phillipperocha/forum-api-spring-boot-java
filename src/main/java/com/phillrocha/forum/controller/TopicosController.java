package com.phillrocha.forum.controller;

import com.phillrocha.forum.controller.dto.TopicoDto;
import com.phillrocha.forum.models.Curso;
import com.phillrocha.forum.models.Topico;
import com.phillrocha.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;


    @RequestMapping("/topics")
    public List<TopicoDto> list() {
        List<Topico> topicos = topicoRepository.findAll();

        return TopicoDto.converter(topicos);
    }
}
