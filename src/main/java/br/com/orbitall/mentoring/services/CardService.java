package br.com.orbitall.mentoring.services;

import br.com.orbitall.mentoring.canonicals.CardInput;
import br.com.orbitall.mentoring.canonicals.CardOutput;
import br.com.orbitall.mentoring.exceptions.ResourceNotFoundException;
import br.com.orbitall.mentoring.models.Card;
import br.com.orbitall.mentoring.repositories.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static br.com.orbitall.mentoring.utilities.MapperUtil.toCanonical;
import static br.com.orbitall.mentoring.utilities.MapperUtil.toModel;

@Service
@Slf4j
public class CardService {
    private final CardRepository repository;

    private CardService(CardRepository repository) {
        this.repository = repository;
    }

    public CardOutput save(CardInput input) {
        Card card = toModel(input);

        LocalDateTime now = LocalDateTime.now();

        card.setStatus(true);
        card.setCreatedAt(now);
        card.setUpdatedAt(now);
        card.setId(UUID.randomUUID());

        return toCanonical(repository.save(card));
    }

    public List<CardOutput> findAll() {
        List<CardOutput> list = new ArrayList<>();

        repository.findAll().forEach(card -> {
            if (card.isStatus()) {
                list.add(toCanonical(card));
            }
        });

        return list;
    }

    public CardOutput findById(UUID id) {
        Card card = repository.findById(id)
                .filter(Card::isStatus)
                .orElseThrow(() -> new ResourceNotFoundException("Not found the resource (id: " + id + ")"));

        return toCanonical(card);
    }

    public CardOutput update(UUID id, CardInput input) {
        Card fetched = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found the resource (id: " + id + ")"));

        fetched.setFullName(input.fullName());
        fetched.setNumber(input.number());
        fetched.setCvv2(input.cvv2());
        fetched.setValidThru(input.validThru());
        fetched.setUpdatedAt(LocalDateTime.now());

        return toCanonical(repository.save(fetched));
    }

    public CardOutput delete(UUID id) {
        Card fetched = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found the resource (id: " + id + ")"));

        fetched.setStatus(false);
        fetched.setUpdatedAt(LocalDateTime.now());

        return toCanonical(repository.save(fetched));
    }
}
