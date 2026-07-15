package immortal.librarymanagementsystem.Controllers;

import immortal.librarymanagementsystem.DTOs.Borrower.BorrowerRequestDTO;
import immortal.librarymanagementsystem.DTOs.Borrower.BorrowerResponseDTO;
import immortal.librarymanagementsystem.Services.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @PostMapping
    public ResponseEntity<BorrowerResponseDTO> createBorrower(@Valid @RequestBody BorrowerRequestDTO borrowerRequestDTO){
        BorrowerResponseDTO createdBorrower = borrowerService.createBorrower(borrowerRequestDTO);

        return new ResponseEntity<>(createdBorrower, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowerResponseDTO> getBorrowerById(@PathVariable Long id){
        BorrowerResponseDTO borrowerResponseDTO = borrowerService.readBorrower(id);

        return ResponseEntity.ok(borrowerResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<BorrowerResponseDTO>> getAllBorrowers(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size){
        Page<BorrowerResponseDTO> borrowers = borrowerService.readAllBorrowers(page,size);

        return ResponseEntity.ok(borrowers);

    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowerResponseDTO> updateBorrower(@PathVariable Long id, @Valid @RequestBody BorrowerRequestDTO borrowerRequestDTO){
        BorrowerResponseDTO borrowerResponseDTO = borrowerService.updateBorrower(id, borrowerRequestDTO);

        return ResponseEntity.ok(borrowerResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrower(@PathVariable Long id){
        borrowerService.deleteBorrower(id);
        return ResponseEntity.noContent().build();
    }

}
