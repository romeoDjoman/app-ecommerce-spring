    package com.romeoDjoman.app_ecommerce_spring.entity;

    import java.util.Collection;
    import java.util.List;
    import java.util.Set;

    import jakarta.persistence.*;
    import lombok.*;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    @Builder
    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "users")
    public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name; //Pseudo
        private String firstName;
        private String lastName;
        private String pwd;
        @Column(unique = true, nullable = false)
        private String email;
        private String photo;
        private boolean active = false;

        @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
        private Cart cart;

        @OneToOne(cascade = CascadeType.ALL)
        private Role role;

        @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Publication> publications;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.role.getLabel().getAuthorities();
        }

        @Override
        public String getPassword() {
            return this.pwd;
        }

        @Override
        public String getUsername() {
            return this.email;
        }

        @Override
        public boolean isAccountNonExpired() {
            return this.active;
        }

        @Override
        public boolean isAccountNonLocked() {
            return this.active;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return this.active;
        }

        @Override
        public boolean isEnabled() {
            return this.active;
        }


        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
        )

        private Set<Role> roles;


    }
