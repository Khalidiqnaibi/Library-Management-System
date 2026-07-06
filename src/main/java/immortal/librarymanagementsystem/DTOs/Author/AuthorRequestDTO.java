package immortal.librarymanagementsystem.DTOs.Author;

import jakarta.validation.constraints.NotNull;

public class AuthorRequestDTO {
    @NotNull(message = "Author name is required")
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
