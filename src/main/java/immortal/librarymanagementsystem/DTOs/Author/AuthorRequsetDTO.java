package immortal.librarymanagementsystem.DTOs.Author;

import jakarta.validation.constraints.NotNull;

public class AuthorRequsetDTO {
    @NotNull(message = "Author name is required")
    private String name;

    public AuthorRequsetDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorRequsetDTO() {
    }
}
