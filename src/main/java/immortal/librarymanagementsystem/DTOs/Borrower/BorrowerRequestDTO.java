package immortal.librarymanagementsystem.DTOs.Borrower;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BorrowerRequestDTO {
    @NotBlank(message = "Borrower name is required")
    @Size(min = 2 , max = 100, message = "Borrower must be between 2 and 100 characters")
    private String name;

    public BorrowerRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BorrowerRequestDTO(String name) {
        this.name = name;
    }
}
