package lobodanicolae.U5_W6_D4_SpringWeb.services;

import lobodanicolae.U5_W6_D4_SpringWeb.entities.Author;
import lobodanicolae.U5_W6_D4_SpringWeb.exceptions.NotFoundException;
import lobodanicolae.U5_W6_D4_SpringWeb.payloads.NewAuthorPayload;
import lobodanicolae.U5_W6_D4_SpringWeb.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private EmailService emailService;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author saveAuthor(NewAuthorPayload payload) {
        Author newAuthor = new Author(
                payload.getNome(),
                payload.getCognome(),
                payload.getEmail(),
                LocalDate.parse(payload.getDataDiNascita())
        );
        authorRepository.save(newAuthor);
        log.info("Author:" + payload.getNome() + " " + payload.getCognome() + " creato con successo!");
        emailService.sendConfirmationEmail(newAuthor.getEmail(), newAuthor.getNome());
        return newAuthor;
    }

    public Author findAuthorById(int authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author non trovato"));
    }

    public Author updateAuthor(int authorId, NewAuthorPayload payload) {
        Author author = findAuthorById(authorId);
        author.setNome(payload.getNome());
        author.setCognome(payload.getCognome());
        author.setEmail(payload.getEmail());
        author.setDataDiNascita(LocalDate.parse(payload.getDataDiNascita()));
        author.setAvatar("https://ui-avatars.com/api/?name=" + payload.getNome() + "+" + payload.getCognome());
        authorRepository.save(author);
        log.info("Author aggiornato: " + payload.getNome() + " " + payload.getCognome());
        return author;
    }

    public boolean findAuthorIdAndDelete(int authorId) {
        Author found = findAuthorById(authorId);
        authorRepository.delete(found);
        return true;
    }

    public Author updateAvatar(int authorId, String avatarUrl) {
        Author author = findAuthorById(authorId);
        author.setAvatar(avatarUrl);
        authorRepository.save(author);
        return author;
    }
}
