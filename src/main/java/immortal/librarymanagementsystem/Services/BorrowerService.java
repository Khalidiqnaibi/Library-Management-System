package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.Entities.Borrower;

public interface BorrowerService {
    void createBorrower(Long id , String name);
    Borrower readBorrower(Long id);
    void updateBorrower(Long id , Borrower borrower);
    void deleteBorrower(Long id);
}
