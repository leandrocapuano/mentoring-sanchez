package br.com.orbitall.mentoring.canonicals;

import lombok.Builder;

@Builder
public record CardInput(String fullName
        , String number
        , String cvv2
        , String validThru) {
}
