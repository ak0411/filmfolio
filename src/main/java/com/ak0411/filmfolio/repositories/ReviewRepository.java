package com.ak0411.filmfolio.repositories;

import com.ak0411.filmfolio.entities.Film;
import com.ak0411.filmfolio.entities.Review;
import com.ak0411.filmfolio.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByFilmAndUser(Film film, User currentUser);
}
