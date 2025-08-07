package br.com.orbitall.mentoring.controllers;

import br.com.orbitall.mentoring.canonicals.CardInput;
import br.com.orbitall.mentoring.canonicals.CardOutput;
import br.com.orbitall.mentoring.services.CardService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cards")
@Slf4j
public class CardController {
    private final CardService service;

    private CardController(CardService service) {
        this.service = service;
    }

    @PostMapping
    public CardOutput save(@Valid @RequestBody CardInput input) {
        return service.save(input);
    }

    @GetMapping
    public List<CardOutput> list() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public CardOutput selectById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("{id}")
    public CardOutput update(@PathVariable UUID id, @RequestBody CardInput input) {
        return service.update(id, input);
    }

    @DeleteMapping("{id}")
    public CardOutput delete(@PathVariable UUID id) {
        return service.delete(id);
    }
}
