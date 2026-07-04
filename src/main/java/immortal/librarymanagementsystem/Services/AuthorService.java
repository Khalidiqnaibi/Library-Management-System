package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Author.AuthorRequsetDTO;
import immortal.librarymanagementsystem.DTOs.Author.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {
    AuthorResponseDTO createAuthor(AuthorRequsetDTO authorRequsetDTO);
    AuthorResponseDTO readAuthor(Long id);
    List<AuthorResponseDTO> readAllAuthors();
    AuthorResponseDTO updateAuthor(Long id,AuthorRequsetDTO authorRequsetDTO);
    void deleteAuthor(Long id);
}
