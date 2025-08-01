package br.com.orbitall.mentoring.canonicals;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CardOutput(UUID id
        , String fullName
        , String number
        , String cvv2
        , String validThru
        , LocalDateTime createdAt
        , LocalDateTime updatedAt
        , boolean status) {

}
