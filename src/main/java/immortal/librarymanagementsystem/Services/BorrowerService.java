package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.Entities.Borrower;

public interface BorrowerService {
    void createBorrower();
    Borrower readBorrower();
    void updateBorrower();
    void deleteBorrower();
}
