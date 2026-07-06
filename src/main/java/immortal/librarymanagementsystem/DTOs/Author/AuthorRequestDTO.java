package immortal.librarymanagementsystem.DTOs.Author;

import jakarta.validation.constraints.*;

public class AuthorRequestDTO {
    @NotBlank(message = "Author name is required")
    @Size(min = 2 ,max = 100 , message = "Author name must be between 2 and 100 characters")
    private String name;

    public AuthorRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorRequestDTO() {
    }
}
