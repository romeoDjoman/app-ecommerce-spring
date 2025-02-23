package com.romeoDjoman.app_ecommerce_spring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.romeoDjoman.app_ecommerce_spring.enums.PermissionType.*;

@Getter
@AllArgsConstructor
public enum RoleType {
    USER(
            Set.of(PermissionType.USER_CREATE_REVIEWS)
    ),
    AUTHOR(
            Set.of(
                    AUTHOR_CREATE_PUBLICATION,
                    AUTHOR_READ,
                    AUTHOR_UPDATE,
                    AUTHOR_DELETE,
                    AUTHOR_DELETE_REVIEWS
            )
    ),
    REVIEWER(
            Set.of(
                    REVIEWER_CREATE,
                    REVIEWER_READ,
                    REVIEWER_UPDATE,
                    REVIEWER_DELETE

            )
    ),
    ADMIN(
            Set.of(
                    ADMIN_CREATE,
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE
        )
    );

    final Set<PermissionType> permissions;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<SimpleGrantedAuthority> grantedAuthorities = this.getPermissions().stream().map(
                permissions -> new SimpleGrantedAuthority(permissions.name())
        ).collect(Collectors.toList());

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }
}