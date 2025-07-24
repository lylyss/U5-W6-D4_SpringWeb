package lobodanicolae.U5_W6_D4_SpringWeb.controllers;

import jakarta.validation.Valid;
import lobodanicolae.U5_W6_D4_SpringWeb.confing.CloudinaryService;
import lobodanicolae.U5_W6_D4_SpringWeb.entities.Author;
import lobodanicolae.U5_W6_D4_SpringWeb.payloads.NewAuthorPayload;
import lobodanicolae.U5_W6_D4_SpringWeb.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CloudinaryService cloudinaryService;

    // 1. GET http://localhost:3001/authors
    @GetMapping
    public List<Author> getAuthors() {
        return this.authorService.findAll();
    }

    // 2. POST http://localhost:3001/authors (+ payload)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody @Valid NewAuthorPayload body) {
        return this.authorService.saveAuthor(body);
    }

    // 3. GET http://localhost:3001/authors/{authorId}
    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable int authorId) {
        return this.authorService.findAuthorById(authorId);
    }

    // 4. PUT http://localhost:3001/authors/{authorId} (+ payload)
    @PutMapping("/{authorId}")
    public Author findAuthorByIdAndUpdate(@RequestBody @Valid NewAuthorPayload body, @PathVariable int authorId) {
        return this.authorService.updateAuthor(authorId, body);
    }

    // 5. DELETE http://localhost:3001/authors/{authorId}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAuthorByIdAndDelete(@PathVariable int authorId) {
        this.authorService.findAuthorIdAndDelete(authorId);
    }

    // 6. POST http://localhost:3001/authors/{authorId}/avatar
    @PostMapping("/{authorId}/avatar")
    public Author uploadAvatar(@PathVariable int authorId, @RequestParam("file") MultipartFile file) {
        String url = cloudinaryService.uploadImage(file);
        return authorService.updateAvatar(authorId, url);
    }
}
