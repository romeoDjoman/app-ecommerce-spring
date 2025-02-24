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
            Set.of(
                    PermissionType.USER_CREATE_REVIEWS,
                    PermissionType.USER_SEARCH_PUBLICATION_BY_KEYWORD,
                    PermissionType.USER_SEARCH_PUBLICATION_BY_FILTER,
                    PermissionType.USER_VIEW_LATEST_PUBLICATIONS,
                    PermissionType.USER_VIEW_TOP_SELLING_PUBLICATIONS,
                    PermissionType.USER_VIEW_TOP_RATED_PUBLICATIONS,
                    PermissionType.USER_VIEW_PUBLICATION_DETAILS,
                    PermissionType.USER_ADD_PUBLICATION_TO_CART,
                    PermissionType.USER_VIEW_CART,
                    PermissionType.USER_REMOVE_PUBLICATION_FROM_CART,
                    PermissionType.USER_CLEAR_CART,
                    PermissionType.USER_PROCESS_PAYMENT,
                    PermissionType.USER_MANAGE_PROFILE,
                    PermissionType.USER_VIEW_ORDER_HISTORY,
                    PermissionType.USER_VIEW_SUBSCRIPTION,
                    PermissionType.USER_CANCEL_SUBSCRIPTION,
                    PermissionType.USER_RENEW_SUBSCRIPTION,
                    PermissionType.USER_VIEW_PUBLICATION,
                    PermissionType.USER_VIEW_ORDER_DETAILS
            )
    ),
    AUTHOR(
            Set.of(
                    PermissionType.AUTHOR_CREATE_PUBLICATION,
                    PermissionType.AUTHOR_READ,
                    PermissionType.AUTHOR_UPDATE,
                    PermissionType.AUTHOR_DELETE,
                    PermissionType.AUTHOR_DELETE_REVIEWS,
                    PermissionType.AUTHOR_VIEW_SUBMITTED_PUBLICATIONS,
                    PermissionType.AUTHOR_VIEW_VALIDATED_PUBLICATIONS,
                    PermissionType.AUTHOR_VIEW_SUBMITTED_WITH_RESPONSE,
                    PermissionType.AUTHOR_COMMUNICATE_WITH_CONTRIBUTORS,
                    PermissionType.AUTHOR_VIEW_UNSUBMITTED_PUBLICATION,
                    PermissionType.AUTHOR_VIEW_VALIDATED_PUBLICATION,
                    PermissionType.AUTHOR_REVIEW_PUBLICATION,
                    PermissionType.AUTHOR_SUBMIT_FOR_REVIEW,
                    PermissionType.AUTHOR_SUBMIT_FINAL_PUBLICATION
            )
    ),
    REVIEWER(
            Set.of(
                    PermissionType.REVIEWER_CREATE,
                    PermissionType.REVIEWER_READ,
                    PermissionType.REVIEWER_UPDATE,
                    PermissionType.REVIEWER_DELETE,
                    PermissionType.REVIEWER_VIEW_PENDING_CONTRIBUTIONS
            )
    ),
    ADMIN(
            Set.of(
                    PermissionType.ADMIN_CREATE,
                    PermissionType.ADMIN_READ,
                    PermissionType.ADMIN_UPDATE,
                    PermissionType.ADMIN_DELETE,
                    PermissionType.ADMIN_VIEW_SUBMITTED_PUBLICATIONS,
                    PermissionType.ADMIN_VIEW_PUBLICATION,
                    PermissionType.ADMIN_MANAGE_CUSTOMERS,
                    PermissionType.ADMIN_CREATE_REVIEWER,
                    PermissionType.ADMIN_UPDATE_CONTRIBUTOR_PROFILE,
                    PermissionType.ADMIN_DISABLE_CONTRIBUTOR
            )
    );

    final Set<PermissionType> permissions;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<SimpleGrantedAuthority> grantedAuthorities = this.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }
}