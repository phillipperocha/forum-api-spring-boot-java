package com.phillrocha.forum.controller;

import com.phillrocha.forum.controller.dto.TopicoDto;
import com.phillrocha.forum.models.Curso;
import com.phillrocha.forum.models.Topico;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {

    @RequestMapping("/topics")
    public List<TopicoDto> list() {
        Curso c1 = new Curso("Spring", "Programação");
        Topico t1 = new Topico("Dúvida", "Dúvida com Spring", c1);

        return TopicoDto.converter(Arrays.asList(t1, t1, t1));
    }
}
