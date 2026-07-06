package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Author.AuthorRequestDTO;
import immortal.librarymanagementsystem.DTOs.Author.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {
    AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO);
    AuthorResponseDTO readAuthor(Long id);
    List<AuthorResponseDTO> readAllAuthors();
    AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO authorRequestDTO);
    void deleteAuthor(Long id);
}
