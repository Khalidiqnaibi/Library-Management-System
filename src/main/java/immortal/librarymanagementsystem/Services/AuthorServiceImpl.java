package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Author.AuthorRequsetDTO;
import immortal.librarymanagementsystem.DTOs.Author.AuthorResponseDTO;
import immortal.librarymanagementsystem.Entities.Author;
import immortal.librarymanagementsystem.Repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorServiceImpl() {
    }

    @Override
    public AuthorResponseDTO createAuthor(AuthorRequsetDTO authorRequsetDTO) {

        return null;
    }

    @Override
    public AuthorResponseDTO readAuthor(Long id) {
        return null;
    }

    @Override
    public List<AuthorResponseDTO> readAllAuthors() {
        return List.of();
    }

    @Override
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequsetDTO authorRequsetDTO) {
        return null;
    }

    @Override
    public void deleteAuthor(Long id) {

    }

    private AuthorResponseDTO ConvertToResponseDTO(Author author){
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setId(author.getId());
        authorResponseDTO.setName(author.getName());
        return  authorResponseDTO;
    }
}
