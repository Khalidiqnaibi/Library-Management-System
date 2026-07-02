package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.Entities.Book;

public interface BookService {
    Book readBook();
    void createBook();
    void updateBook();
    void deleteBook();
}
