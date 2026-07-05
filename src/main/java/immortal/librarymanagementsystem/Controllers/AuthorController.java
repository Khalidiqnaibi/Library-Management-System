package immortal.librarymanagementsystem.Controllers;

import immortal.librarymanagementsystem.DTOs.Author.AuthorRequsetDTO;
import immortal.librarymanagementsystem.DTOs.Author.AuthorResponseDTO;
import immortal.librarymanagementsystem.Services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRequsetDTO authorRequsetDTO){
        AuthorResponseDTO createdAuthor = authorService.createAuthor(authorRequsetDTO);

        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id){
        AuthorResponseDTO authorResponseDTO = ;
    }
}
