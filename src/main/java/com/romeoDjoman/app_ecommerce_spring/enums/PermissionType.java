package com.romeoDjoman.app_ecommerce_spring.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PermissionType {
    ADMIN_CREATE,
    ADMIN_READ,
    ADMIN_UPDATE,
    ADMIN_DELETE,

    AUTHOR_CREATE_PUBLICATION,
    AUTHOR_READ,
    AUTHOR_UPDATE,
    AUTHOR_DELETE,
    AUTHOR_DELETE_REVIEWS,


    REVIEWER_CREATE,
    REVIEWER_READ,
    REVIEWER_UPDATE,
    REVIEWER_DELETE,

    USER_CREATE_REVIEWS;

    @Getter
    private String label;
}
