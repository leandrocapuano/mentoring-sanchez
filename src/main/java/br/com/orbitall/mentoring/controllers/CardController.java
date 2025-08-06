package br.com.orbitall.mentoring.controllers;

import br.com.orbitall.mentoring.canonicals.CardInput;
import br.com.orbitall.mentoring.canonicals.CardOutput;
import br.com.orbitall.mentoring.models.Card;
import br.com.orbitall.mentoring.services.CardService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.orbitall.mentoring.utilities.MapperUtil.toCanonical;
import static br.com.orbitall.mentoring.utilities.MapperUtil.toModel;

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
        Card card = toModel(input);
        service.save(card);
        return toCanonical(card);
    }

    @GetMapping
    public Iterable<Card> list() {
        return this.service.list();
    }

    @GetMapping("{id}")
    public Card selectById(@PathVariable UUID id) {
        return service.retrieve(id);
    }

    @PutMapping("{id}")
    public Card update(@PathVariable UUID id, @RequestBody CardInput input) {
        log.info("Request info {}", input);

        Card card = Card.builder()
                .number(input.number())
                .cvv2(input.cvv2())
                .fullName(input.fullName())
                .validThru(input.validThru())
                .build();

        return service.update(id, card);
    }

    @DeleteMapping("{id}")
    public Card delete(@PathVariable UUID id) {
        log.info("Request info {}", id);

        return service.delete(id);
    }


}
