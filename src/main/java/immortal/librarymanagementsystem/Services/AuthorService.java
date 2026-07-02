package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.Entities.Author;

public interface AuthorService {
    void createAuthor();
    Author readAuthor();
    void updateAuthor();
    void deleteAuthor();
}
