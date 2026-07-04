package immortal.librarymanagementsystem.DTOs.Borrower;

import jakarta.validation.constraints.NotNull;

public class BorrowerRequestDTO {
    @NotNull(message = "Borrower name is required")
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
