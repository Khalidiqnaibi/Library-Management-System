package immortal.librarymanagementsystem.Repositories;

import immortal.librarymanagementsystem.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
