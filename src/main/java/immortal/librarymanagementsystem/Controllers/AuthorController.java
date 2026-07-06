package immortal.librarymanagementsystem.Controllers;

import immortal.librarymanagementsystem.DTOs.Author.AuthorRequestDTO;
import immortal.librarymanagementsystem.DTOs.Author.AuthorResponseDTO;
import immortal.librarymanagementsystem.Services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRequestDTO authorRequestDTO){
        AuthorResponseDTO createdAuthor = authorService.createAuthor(authorRequestDTO);

        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id){
        AuthorResponseDTO authorResponseDTO = authorService.readAuthor(id);

        return ResponseEntity.ok(authorResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors(){
        List<AuthorResponseDTO> authors = authorService.readAllAuthors();

        return ResponseEntity.ok(authors);

    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorRequestDTO authorRequestDTO){
        AuthorResponseDTO authorResponseDTO = authorService.updateAuthor(id, authorRequestDTO);

        return ResponseEntity.ok(authorResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

}
