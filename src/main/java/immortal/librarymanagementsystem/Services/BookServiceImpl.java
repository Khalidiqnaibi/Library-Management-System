package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Book.BookRequestDTO;
import immortal.librarymanagementsystem.DTOs.Book.BookResponseDTO;
import immortal.librarymanagementsystem.Entities.Author;
import immortal.librarymanagementsystem.Entities.Category;
import immortal.librarymanagementsystem.Repositories.AuthorRepository;
import immortal.librarymanagementsystem.Repositories.BookRepository;
import immortal.librarymanagementsystem.Entities.Book;
import immortal.librarymanagementsystem.Repositories.BorrowerRepository;
import immortal.librarymanagementsystem.Repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BorrowerRepository borrowerRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, BorrowerRepository borrowerRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public BookResponseDTO readBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book with the ID: "+id+" not found"));
        return ConvertToResponseDTO(book);
    }

    @Override
    public List<BookResponseDTO> readAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::ConvertToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());

        Long authorId = bookRequestDTO.getAuthorId();
        Author author = authorRepository.findById(authorId).orElseThrow(()-> new RuntimeException("Creating book failed.. no Author with ID: "+authorId+" was found"));
        book.setAuthor(author);

        Long categoryId = bookRequestDTO.getCategoryID();
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new RuntimeException("Creating book failed.. no Category with ID: "+categoryId+" was found"));
        book.setCategory(category);

        book.setBorrower(null);

        Book savedBook = bookRepository.save(book);

        return ConvertToResponseDTO(savedBook);
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Update failed.. no book with ID: "+id+" was found"));
        book.setTitle(bookRequestDTO.getTitle());

        Long authorId = bookRequestDTO.getAuthorId();
        Author author = authorRepository.findById(authorId).orElseThrow(()-> new RuntimeException("update book failed.. no Author with ID: "+authorId+" was found"));
        book.setAuthor(author);

        Long categoryId = bookRequestDTO.getCategoryID();
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new RuntimeException("update book failed.. no Category with ID: "+categoryId+" was found"));
        book.setCategory(category);

        Book savedBook = bookRepository.save(book);

        return ConvertToResponseDTO(savedBook);
    }

    @Override
    public BookResponseDTO readBookByTitle(String title) {
        return null;
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Delete failed.. no book with ID: "+id+" was found"));

        bookRepository.delete(book);
    }

    public BookResponseDTO ConvertToResponseDTO(Book book){
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId(book.getId());
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setBorrowed(book.isBorrowed());
        bookResponseDTO.setAuthorName(book.getAuthor().getName());
        bookResponseDTO.setCategoryName(book.getCategory().getName());

        if (book.isBorrowed()){
            bookResponseDTO.setBorrowerName(book.getBorrower().getName());
        }else{
            bookResponseDTO.setBorrowerName(null);
        }

        return bookResponseDTO;
    }
}
