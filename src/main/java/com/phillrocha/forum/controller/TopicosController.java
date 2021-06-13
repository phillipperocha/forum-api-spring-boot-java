package com.phillrocha.forum.controller;

import com.phillrocha.forum.controller.dto.TopicoDetailsDto;
import com.phillrocha.forum.controller.dto.TopicoDto;
import com.phillrocha.forum.controller.form.TopicoForm;
import com.phillrocha.forum.controller.form.TopicoUpdateForm;
import com.phillrocha.forum.models.Topico;
import com.phillrocha.forum.repository.CursoRepository;
import com.phillrocha.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;


    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping
    public Page<TopicoDto> list(@RequestParam(required = false) String tituloTopico,
                                @RequestParam(required = false) String nomeCurso,
                                @PageableDefault(sort="id", direction=Direction.ASC, page = 0, size = 10) Pageable paginacao) {

        Page<Topico> topicos;

        if (nomeCurso == null && tituloTopico == null) {
            topicos = topicoRepository.findAll(paginacao);
        } else if (tituloTopico != null) {
            topicos = topicoRepository.findByTitulo(tituloTopico, paginacao);

        } else {
            topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
        }

        return TopicoDto.converter(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetailsDto> detalhar(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            Topico topico = topicoRepository.getById(id);
            return ResponseEntity.ok(new TopicoDetailsDto(topico));
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoUpdateForm form) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDto(topico));
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

}
