package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Borrower.BorrowerRequestDTO;
import immortal.librarymanagementsystem.DTOs.Borrower.BorrowerResponseDTO;

import java.util.List;

public interface BorrowerService {
    BorrowerResponseDTO createBorrower(BorrowerRequestDTO borrowerRequestDTO);
    BorrowerResponseDTO readBorrower(Long id);
    List<BorrowerResponseDTO> readAllBorrowers();
    BorrowerResponseDTO updateBorrower(Long id , BorrowerRequestDTO borrowerRequestDTO);
    void deleteBorrower(Long id);
}
