package com.lcaohoanq.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserGenderEnum {

    MALE("M"),
    FEMALE("F"),
    OTHER("O");

    private final String gender;
}
