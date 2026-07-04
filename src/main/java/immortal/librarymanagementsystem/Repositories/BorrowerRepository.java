package immortal.librarymanagementsystem.Repositories;

import immortal.librarymanagementsystem.Entities.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower,Long> {
}
