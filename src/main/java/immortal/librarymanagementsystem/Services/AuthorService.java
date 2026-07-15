package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Author.AuthorRequestDTO;
import immortal.librarymanagementsystem.DTOs.Author.AuthorResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {
    AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO);
    AuthorResponseDTO readAuthor(Long id);
    Page<AuthorResponseDTO> readAllAuthors(int page , int size);
    AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO authorRequestDTO);
    void deleteAuthor(Long id);
}
