package lobodanicolae.U5_W6_D4_SpringWeb.repositories;

import lobodanicolae.U5_W6_D4_SpringWeb.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
}
