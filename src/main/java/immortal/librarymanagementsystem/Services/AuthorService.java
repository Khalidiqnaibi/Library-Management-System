package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.Entities.Author;

public interface AuthorService {
    void createAuthor(Long id,String name);
    Author readAuthor(Long id);
    void updateAuthor(Long id,Author author);
    void deleteAuthor(Long id);
}
