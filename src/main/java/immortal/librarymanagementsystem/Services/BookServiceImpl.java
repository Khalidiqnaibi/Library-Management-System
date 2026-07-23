package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Book.BookRequestDTO;
import immortal.librarymanagementsystem.DTOs.Book.BookResponseDTO;
import immortal.librarymanagementsystem.Entities.Author;
import immortal.librarymanagementsystem.Entities.Borrower;
import immortal.librarymanagementsystem.Entities.Category;
import immortal.librarymanagementsystem.Exceptions.BookAlreadyBorrowedException;
import immortal.librarymanagementsystem.Exceptions.BookNotBorrowed;
import immortal.librarymanagementsystem.Exceptions.ResourceNotFoundException;
import immortal.librarymanagementsystem.Repositories.AuthorRepository;
import immortal.librarymanagementsystem.Repositories.BookRepository;
import immortal.librarymanagementsystem.Entities.Book;
import immortal.librarymanagementsystem.Repositories.BorrowerRepository;
import immortal.librarymanagementsystem.Repositories.CategoryRepository;
import immortal.librarymanagementsystem.Specifications.BookSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
        Book book = bookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Book with the ID: "+id+" not found"));
        return ConvertToResponseDTO(book);
    }

    @Override
    public Page<BookResponseDTO> readAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(this::ConvertToResponseDTO);
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());

        Long authorId = bookRequestDTO.getAuthorId();
        Author author = authorRepository.findById(authorId).orElseThrow(()-> new ResourceNotFoundException("Creating book failed.. no Author with ID: "+authorId+" was found"));
        book.setAuthor(author);

        Long categoryId = bookRequestDTO.getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Creating book failed.. no Category with ID: "+categoryId+" was found"));
        book.setCategory(category);

        book.setBorrower(null);

        Book savedBook = bookRepository.save(book);

        return ConvertToResponseDTO(savedBook);
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Update failed.. no Book with ID: "+id+" was found"));
        book.setTitle(bookRequestDTO.getTitle());

        Long authorId = bookRequestDTO.getAuthorId();
        Author author = authorRepository.findById(authorId).orElseThrow(()-> new ResourceNotFoundException("update Book failed.. no Author with ID: "+authorId+" was found"));
        book.setAuthor(author);

        Long categoryId = bookRequestDTO.getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("update Book failed.. no Category with ID: "+categoryId+" was found"));
        book.setCategory(category);

        Book savedBook = bookRepository.save(book);

        return ConvertToResponseDTO(savedBook);
    }

    @Override
    public Page<BookResponseDTO> searchBook(String title, Long authorId, Long categoryId, Boolean availableOnly,int page , int size) {
        Pageable pageable = PageRequest.of(page,size);
        boolean safeAvailableOnly = Boolean.TRUE.equals(availableOnly);

        Specification<Book> spec = BookSpecifications.filterBooks(title, authorId, categoryId, safeAvailableOnly);


        Page<Book> books = bookRepository.findAll(spec,pageable);

        return books.map(this::ConvertToResponseDTO);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Delete failed.. no Book with ID: "+id+" was found"));

        bookRepository.delete(book);
    }

    @Override
    public BookResponseDTO borrowBook(Long bookId, Long borrowerId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Borrowing failed.. no Book with ID: "+bookId+" was found"));

        Borrower borrower = borrowerRepository.findById(borrowerId).orElseThrow(()-> new ResourceNotFoundException("Borrowing failed.. no Borrower with ID: "+borrowerId+" was found"));
        if(book.isBorrowed()){
            throw new BookAlreadyBorrowedException("Borrowing failed.. Book with ID: "+bookId+" is already borrowed");
        }
        book.setBorrower(borrower);
        Book savedBook = bookRepository.save(book);
        return ConvertToResponseDTO(savedBook);
    }

    @Override
    public void returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Returning failed.. no Book with ID: "+bookId+" was found"));
        if(!book.isBorrowed()){
            throw new BookNotBorrowed("Returning failed.. Book with ID: "+bookId+" is not borrowed");
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
