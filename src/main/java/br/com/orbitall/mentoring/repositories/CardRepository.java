package br.com.orbitall.mentoring.repositories;

import br.com.orbitall.mentoring.models.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardRepository extends CrudRepository<Card, UUID> { }