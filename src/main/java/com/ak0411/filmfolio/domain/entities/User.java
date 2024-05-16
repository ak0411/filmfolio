package com.ak0411.filmfolio.domain.entities;

import com.ak0411.filmfolio.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@JsonIncludeProperties({"id", "name", "username", "favoriteFilms", "reviews"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 100)
    private String name;

    @Column(unique = true, nullable = false, updatable = false, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    private UserRole role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_film",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    @JsonIncludeProperties({"title", "release_date", "genres", "imdb_id"})
    private Set<Film> favoriteFilms;

    @OneToMany(mappedBy = "user")
    @JsonIncludeProperties({"text", "rating", "film"})
    private List<Review> reviews;

    public User(String name, String username, String password, UserRole role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addFavorite(Film film) {
        this.favoriteFilms.add(film);
    }

    public void removeFavorite(Film film) {
        this.favoriteFilms.remove(film);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }
}
