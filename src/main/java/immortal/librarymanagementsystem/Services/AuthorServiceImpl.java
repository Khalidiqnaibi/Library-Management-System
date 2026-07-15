package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Author.AuthorRequestDTO;
import immortal.librarymanagementsystem.DTOs.Author.AuthorResponseDTO;
import immortal.librarymanagementsystem.Entities.Author;
import immortal.librarymanagementsystem.Exceptions.ResourceNotFoundException;
import immortal.librarymanagementsystem.Repositories.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO) {
        Author author = new Author();
        author.setName(authorRequestDTO.getName());
        Author savedAuthor = authorRepository.save(author);
        return ConvertToResponseDTO(savedAuthor);
    }

    @Override
    public AuthorResponseDTO readAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author ID: "+ id + " not found.."));
        return ConvertToResponseDTO(author);
    }

    @Override
    public Page<AuthorResponseDTO> readAllAuthors(int page , int size) {
        Pageable pageable = PageRequest.of(page,size);

        Page<Author> authors = authorRepository.findAll(pageable);

        return authors.map(this::ConvertToResponseDTO);

    }

    @Override
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO authorRequestDTO) {
        Author author = authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Update failed.. Author not found with the ID: "+id));
        author.setName(authorRequestDTO.getName());
        Author updatedAuthor = authorRepository.save(author);
        return ConvertToResponseDTO(updatedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Delete failed.. Author not found with the ID: "+id));

        authorRepository.delete(author);

    }

    private AuthorResponseDTO ConvertToResponseDTO(Author author){
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setId(author.getId());
        authorResponseDTO.setName(author.getName());
        return  authorResponseDTO;
    }
}
