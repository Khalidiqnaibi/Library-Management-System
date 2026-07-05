package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Author.AuthorRequsetDTO;
import immortal.librarymanagementsystem.DTOs.Author.AuthorResponseDTO;
import immortal.librarymanagementsystem.Entities.Author;
import immortal.librarymanagementsystem.Repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorResponseDTO createAuthor(AuthorRequsetDTO authorRequsetDTO) {
        Author author = new Author();
        author.setName(authorRequsetDTO.getName());
        Author savedAuthor = authorRepository.save(author);
        return ConvertToResponseDTO(savedAuthor);
    }

    @Override
    public AuthorResponseDTO readAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author ID: "+ id + " not found.."));
        return ConvertToResponseDTO(author);
    }

    @Override
    public List<AuthorResponseDTO> readAllAuthors() {
        List<Author> authors = authorRepository.findAll();

        return authors.stream().map(this::ConvertToResponseDTO).collect(Collectors.toList());

    }

    @Override
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequsetDTO authorRequsetDTO) {
        Author author = authorRepository.findById(id).orElseThrow(()-> new RuntimeException("Update failed.. Author not found with the ID: "+id));
        author.setName(authorRequsetDTO.getName());
        Author updatedAuthor = authorRepository.save(author);
        return ConvertToResponseDTO(updatedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(()->new RuntimeException("Delete failed.. Author not found with the ID: "+id));

        authorRepository.delete(author);

    }

    private AuthorResponseDTO ConvertToResponseDTO(Author author){
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setId(author.getId());
        authorResponseDTO.setName(author.getName());
        return  authorResponseDTO;
    }
}
