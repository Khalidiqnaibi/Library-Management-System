package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.Entities.Author;
import immortal.librarymanagementsystem.Entities.Book;
import immortal.librarymanagementsystem.Entities.Category;

public interface BookService {
    Book readBook(Long id);
    void createBook(Long id , String title, Category category , Author author);
    void updateBook(Long id, Book book);
    Book readBookByTitle(String title);
    void deleteBook(Long id);
}
