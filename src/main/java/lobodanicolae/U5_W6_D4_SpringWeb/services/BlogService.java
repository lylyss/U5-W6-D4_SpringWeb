package lobodanicolae.U5_W6_D4_SpringWeb.services;

import lobodanicolae.U5_W6_D4_SpringWeb.entities.Author;
import lobodanicolae.U5_W6_D4_SpringWeb.entities.BlogPost;
import lobodanicolae.U5_W6_D4_SpringWeb.exceptions.NotFoundException;
import lobodanicolae.U5_W6_D4_SpringWeb.payloads.NewBlogPostPayload;
import lobodanicolae.U5_W6_D4_SpringWeb.repositories.AuthorRepository;
import lobodanicolae.U5_W6_D4_SpringWeb.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogPostRepository blogPostRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }

    public Page<BlogPost> findAllPaged(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return blogPostRepository.findAll(pageable);
    }

    public BlogPost save(NewBlogPostPayload payload) {
        BlogPost newPost = new BlogPost(
                payload.getCategoria(),
                payload.getTitolo(),
                payload.getContenuto(),
                payload.getTempoDiLettura()
        );
        if (payload.getAuthorId() != null) {
            Author author = authorRepository.findById(payload.getAuthorId())
                    .orElseThrow(() -> new NotFoundException("Author non trovato"));
            newPost.setAuthor(author);
        }
        newPost.setCover("https://picsum.photos/200/300?random=");
        blogPostRepository.save(newPost);
        return newPost;
    }

    public BlogPost findById(int id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("BlogPost non trovato"));
    }

    public BlogPost update(int id, NewBlogPostPayload payload) {
        BlogPost bp = findById(id);
        bp.setCategoria(payload.getCategoria());
        bp.setTitolo(payload.getTitolo());
        bp.setContenuto(payload.getContenuto());
        bp.setTempoDiLettura(payload.getTempoDiLettura());
        bp.setCover("https://picsum.photos/200/300?random=" + bp.getId());
        if (payload.getAuthorId() != null) {
            Author author = authorRepository.findById(payload.getAuthorId())
                    .orElseThrow(() -> new NotFoundException("Author non trovato"));
            bp.setAuthor(author);
        }
        blogPostRepository.save(bp);
        return bp;
    }

    public BlogPost updateCover(int id, String coverUrl) {
        BlogPost bp = findById(id);
        bp.setCover(coverUrl);
        blogPostRepository.save(bp);
        return bp;
    }

    public void delete(int id) {
        BlogPost found = findById(id);
        blogPostRepository.delete(found);
    }
}
