package com.phillrocha.forum.controller;

import com.phillrocha.forum.controller.dto.TopicoDto;
import com.phillrocha.forum.controller.form.TopicoForm;
import com.phillrocha.forum.models.Topico;
import com.phillrocha.forum.repository.CursoRepository;
import com.phillrocha.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;
    // Precisamos injetar o CursoRepository aqui no nosso Controller
    @Autowired
    private CursoRepository cursoRepository;


    @PostMapping
    public void cadastrar(@RequestBody TopicoForm form) {
        // E agora vou obter o topico através da conversão do form
        Topico topico = form.converter(cursoRepository);
        // Com o tópico em mãos iremos salvá-lo no banco de dados
        topicoRepository.save(topico);
    }

    @GetMapping
    public List<TopicoDto> list(String tituloTopico, String nomeCurso) {
        List<Topico> topicos = null;

        if (nomeCurso == null && tituloTopico == null) {
            topicos = topicoRepository.findAll();
        } else if (tituloTopico != null) {
            topicos = topicoRepository.findByTitulo(tituloTopico);

        } else {
            topicos = topicoRepository.findByCursoNome(nomeCurso);
        }

        return TopicoDto.converter(topicos);
    }

}
