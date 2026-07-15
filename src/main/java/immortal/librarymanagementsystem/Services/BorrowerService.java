package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Borrower.BorrowerRequestDTO;
import immortal.librarymanagementsystem.DTOs.Borrower.BorrowerResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BorrowerService {
    BorrowerResponseDTO createBorrower(BorrowerRequestDTO borrowerRequestDTO);
    BorrowerResponseDTO readBorrower(Long id);
    Page<BorrowerResponseDTO> readAllBorrowers(int page , int size);
    BorrowerResponseDTO updateBorrower(Long id , BorrowerRequestDTO borrowerRequestDTO);
    void deleteBorrower(Long id);
}
