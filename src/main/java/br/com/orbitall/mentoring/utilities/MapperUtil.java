package br.com.orbitall.mentoring.utilities;

import br.com.orbitall.mentoring.canonicals.CardInput;
import br.com.orbitall.mentoring.canonicals.CardOutput;
import br.com.orbitall.mentoring.models.Card;

public final class MapperUtil extends Object {

    private MapperUtil() {
        super();
    }

    public static Card toModel(CardInput input) {
        return Card.builder()
                .number(input.number())
                .cvv2(input.cvv2())
                .fullName(input.fullName())
                .validThru(input.validThru())
                .build();
    }

    public static CardOutput toCanonical(Card card) {
        return CardOutput.builder()
                .id(card.getId())
                .number(card.getNumber())
                .cvv2(card.getCvv2())
                .validThru(card.getValidThru())
                .fullName(card.getFullName())
                .status(card.isStatus())
                .createdAt(card.getCreatedAt())
                .updatedAt(card.getUpdatedAt())
                .build();
    }

}
