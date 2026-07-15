package immortal.librarymanagementsystem.Repositories;

import immortal.librarymanagementsystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> , JpaSpecificationExecutor<Book> {
}
