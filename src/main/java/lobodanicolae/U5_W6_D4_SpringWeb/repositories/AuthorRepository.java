package lobodanicolae.U5_W6_D4_SpringWeb.repositories;

import lobodanicolae.U5_W6_D4_SpringWeb.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}

