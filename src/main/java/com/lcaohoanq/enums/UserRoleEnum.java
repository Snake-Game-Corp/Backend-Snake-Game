package com.lcaohoanq.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleEnum {

    USER(0),
    USER_GOLD(1),
    USER_PREMIUM(2),
    EMPLOYEE(3),
    ADMIN(4);

    private final int role;

}
