package lobodanicolae.U5_W6_D4_SpringWeb.controllers;

import jakarta.validation.Valid;
import lobodanicolae.U5_W6_D4_SpringWeb.confing.CloudinaryService;
import lobodanicolae.U5_W6_D4_SpringWeb.entities.BlogPost;
import lobodanicolae.U5_W6_D4_SpringWeb.payloads.NewBlogPostPayload;
import lobodanicolae.U5_W6_D4_SpringWeb.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostsController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public List<BlogPost> getAll() {
        return blogService.findAll();
    }

    @GetMapping("/paged")
    public Page<BlogPost> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return blogService.findAllPaged(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost create(@RequestBody @Valid NewBlogPostPayload body) {
        return blogService.save(body);
    }

    @GetMapping("/{id}")
    public BlogPost getById(@PathVariable int id) {
        return blogService.findById(id);
    }

    @PutMapping("/{id}")
    public BlogPost update(@PathVariable int id, @RequestBody @Valid NewBlogPostPayload body) {
        return blogService.update(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        blogService.delete(id);
    }

    @PostMapping("/{id}/cover")
    public BlogPost uploadCover(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        String url = cloudinaryService.uploadImage(file);
        return blogService.updateCover(id, url);
    }
}
