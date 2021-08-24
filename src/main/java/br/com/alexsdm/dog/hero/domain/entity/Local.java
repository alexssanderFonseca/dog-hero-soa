package br.com.alexsdm.dog.hero.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Local {
    private final String latitude;
    private final String longitude;
}
