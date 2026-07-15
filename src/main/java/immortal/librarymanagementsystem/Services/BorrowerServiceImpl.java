package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Borrower.BorrowerRequestDTO;
import immortal.librarymanagementsystem.DTOs.Borrower.BorrowerResponseDTO;
import immortal.librarymanagementsystem.Entities.Borrower;
import immortal.librarymanagementsystem.Exceptions.ResourceNotFoundException;
import immortal.librarymanagementsystem.Repositories.BorrowerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowerServiceImpl implements BorrowerService{
    private final BorrowerRepository borrowerRepository;

    public BorrowerServiceImpl(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public BorrowerResponseDTO createBorrower(BorrowerRequestDTO borrowerRequestDTO) {
        Borrower borrower = new Borrower();
        borrower.setName(borrowerRequestDTO.getName());

        Borrower savedBorrower = borrowerRepository.save(borrower);
        return ConvertToResponseDTO(savedBorrower);
    }

    @Override
    public BorrowerResponseDTO readBorrower(Long id) {
        Borrower borrower = borrowerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Borrower with ID: "+id+" Not found"));

        return ConvertToResponseDTO(borrower);
    }

    @Override
    public Page<BorrowerResponseDTO> readAllBorrowers(int page , int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Borrower> borrowers = borrowerRepository.findAll(pageable);

        return borrowers.map(this::ConvertToResponseDTO);

    }

    @Override
    public BorrowerResponseDTO updateBorrower(Long id, BorrowerRequestDTO borrowerRequestDTO) {
        Borrower borrower = borrowerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Update failed.. no Borrower with ID: "+id));

        borrower.setName(borrowerRequestDTO.getName());
        Borrower savedBorrower = borrowerRepository.save(borrower);
        return ConvertToResponseDTO(savedBorrower);
    }

    @Override
    public void deleteBorrower(Long id) {
        Borrower borrower = borrowerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Delete failed.. no Borrower with ID: "+id));
        borrowerRepository.delete(borrower);
    }

    public BorrowerResponseDTO ConvertToResponseDTO(Borrower borrower){
        BorrowerResponseDTO borrowerResponseDTO = new BorrowerResponseDTO();
        borrowerResponseDTO.setName(borrower.getName());
        borrowerResponseDTO.setId(borrower.getId());
        return borrowerResponseDTO;
    }
}
