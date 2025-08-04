package br.com.orbitall.mentoring.services;

import br.com.orbitall.mentoring.models.Card;
import br.com.orbitall.mentoring.repositories.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CardService {
    @Autowired
    private CardRepository repository;

    public Card create(Card card) {
        LocalDateTime now = LocalDateTime.now();

        card.setStatus(true);
        card.setCreatedAt(now);
        card.setUpdatedAt(now);
        card.setId(UUID.randomUUID());

        log.info("Model info {}", card);

        return this.repository.save(card);
    }

    public Iterable<Card> list() {
        return this.repository.findAll();
    }

    public Card retrieve(UUID id) {
        return repository.findById(id).get();
    }

    public Card update(UUID id, Card card) {
        Card fetched = retrieve(id);

        fetched.setFullName(card.getFullName());
        fetched.setNumber(card.getNumber());
        fetched.setCvv2(card.getCvv2());
        fetched.setValidThru(card.getValidThru());
        fetched.setUpdatedAt(LocalDateTime.now());

        log.info("Model info {}", fetched);

        return repository.save(fetched);
    }

    public Card delete(UUID id) {
        Card fetched = retrieve(id);

        fetched.setStatus(false);
        fetched.setUpdatedAt(LocalDateTime.now());

        log.info("Model info {}", fetched);

        return repository.save(fetched);

    }
}
