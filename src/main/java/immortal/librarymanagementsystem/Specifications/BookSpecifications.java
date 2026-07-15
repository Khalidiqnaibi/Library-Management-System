package immortal.librarymanagementsystem.Specifications;

import immortal.librarymanagementsystem.Entities.Book;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class BookSpecifications {

    public static Specification<Book> filterBooks(String title, Long authorId, Long categoryId ,Boolean availableOnly){
        return (root,query,criteriaBuilder)->{
            List<Predicate> predicates = new ArrayList<>();

            if (title!=null&&!title.trim().isBlank()){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),"%"+title.toLowerCase()+"%"
                        )
                );
            }

            if (authorId != null){
                predicates.add(criteriaBuilder.equal(root.get("author"),authorId));
            }

            if (categoryId != null){
                predicates.add(criteriaBuilder.equal(root.get("category"),categoryId));
            }

            if (availableOnly){
                predicates.add(criteriaBuilder.equal(root.get("borrower"),null));
            }

            return  criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}