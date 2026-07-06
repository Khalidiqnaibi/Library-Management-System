package immortal.librarymanagementsystem.Repositories;

import immortal.librarymanagementsystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorId(Long authorId);
    List<Book> findByCategoryId(Long categoryId);
    List<Book> findByBorrowerIsNull();
}
