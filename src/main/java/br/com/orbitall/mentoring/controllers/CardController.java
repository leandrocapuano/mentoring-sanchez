package br.com.orbitall.mentoring.controllers;

import br.com.orbitall.mentoring.canonicals.CardInput;
import br.com.orbitall.mentoring.canonicals.CardOutput;
import br.com.orbitall.mentoring.models.Card;
import br.com.orbitall.mentoring.services.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
    GET     /cards          OK
    GET     /cards/{id}     OK
    POST    /cards          OK
    PUT     /cards/{id}     Pending...
    DELETE  /cards/{id}     Pending...
 */
@RestController
@RequestMapping("cards")
@Slf4j
public class CardController {
    @Autowired
    private CardService service;

    @PostMapping
    public CardOutput save(@RequestBody CardInput input) {
        log.info("Request info {}", input);

        Card card = Card.builder()
                .number(input.number())
                .cvv2(input.cvv2())
                .fullName(input.fullName())
                .validThru(input.validThru())
                .build();

        service.create(card);

        CardOutput output =  CardOutput.builder()
                .id(card.getId())
                .number(card.getNumber())
                .cvv2(card.getCvv2())
                .validThru(card.getValidThru())
                .fullName(card.getFullName())
                .status(card.isStatus())
                .createdAt(card.getCreatedAt())
                .updatedAt(card.getUpdatedAt())
                .build();

        log.info("Response info {}", output);
        return output;
    }

    @GetMapping
    public Iterable<Card> list() {
        return this.service.list();
    }

    @GetMapping("/{id}")
    public Card selectById(@PathVariable UUID id) {
        return service.retrieve(id);
    }
}
