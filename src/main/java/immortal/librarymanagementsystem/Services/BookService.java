package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Book.BookRequestDTO;
import immortal.librarymanagementsystem.DTOs.Book.BookResponseDTO;

import java.util.List;

public interface BookService {
    BookResponseDTO readBook(Long id);
    List<BookRequestDTO> readAllBooks();
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO);
    BookResponseDTO readBookByTitle(String title);
    void deleteBook(Long id);
}
