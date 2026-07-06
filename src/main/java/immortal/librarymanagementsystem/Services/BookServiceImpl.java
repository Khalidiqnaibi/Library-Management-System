package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Book.BookRequestDTO;
import immortal.librarymanagementsystem.DTOs.Book.BookResponseDTO;
import immortal.librarymanagementsystem.Entities.Author;
import immortal.librarymanagementsystem.Entities.Borrower;
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

        Long categoryId = bookRequestDTO.getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new RuntimeException("Creating book failed.. no Category with ID: "+categoryId+" was found"));
        book.setCategory(category);

        book.setBorrower(null);

        Book savedBook = bookRepository.save(book);

        return ConvertToResponseDTO(savedBook);
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Update failed.. no Book with ID: "+id+" was found"));
        book.setTitle(bookRequestDTO.getTitle());

        Long authorId = bookRequestDTO.getAuthorId();
        Author author = authorRepository.findById(authorId).orElseThrow(()-> new RuntimeException("update Book failed.. no Author with ID: "+authorId+" was found"));
        book.setAuthor(author);

        Long categoryId = bookRequestDTO.getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new RuntimeException("update Book failed.. no Category with ID: "+categoryId+" was found"));
        book.setCategory(category);

        Book savedBook = bookRepository.save(book);

        return ConvertToResponseDTO(savedBook);
    }

    @Override
    public List<BookResponseDTO> findBookByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);

        return books.stream().map(this::ConvertToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDTO> filterBooksByCategory(Long categoryId) {
        List<Book> books = bookRepository.findByCategoryId(categoryId);
        return books.stream().map(this::ConvertToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDTO> filterBooksByAuthor(Long authorId) {
        List<Book> books = bookRepository.findByAuthorId(authorId);

        return books.stream().map(this::ConvertToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDTO> readAvailableBooks() {
        List<Book> books = bookRepository.findByBorrowerIsNull();
        return books.stream().map(this::ConvertToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Delete failed.. no Book with ID: "+id+" was found"));

        bookRepository.delete(book);
    }

    @Override
    public BookResponseDTO borrowBook(Long bookId, Long borrowerId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Borrowing failed.. no Book with ID: "+bookId+" was found"));

        Borrower borrower = borrowerRepository.findById(borrowerId).orElseThrow(()-> new RuntimeException("Borrowing failed.. no Borrower with ID: "+borrowerId+" was found"));
        if(book.isBorrowed()){
            throw new RuntimeException("Borrowing failed.. Book with ID: "+bookId+" is already borrowed");
        }
        book.setBorrower(borrower);
        Book savedBook = bookRepository.save(book);
        return ConvertToResponseDTO(savedBook);
    }

    @Override
    public void returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Returning failed.. no Book with ID: "+bookId+" was found"));
        if(!book.isBorrowed()){
            throw new RuntimeException("Returning failed.. Book with ID: "+bookId+" is not borrowed");
        }
        book.setBorrower(null);
        Book savedBook = bookRepository.save(book);
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
