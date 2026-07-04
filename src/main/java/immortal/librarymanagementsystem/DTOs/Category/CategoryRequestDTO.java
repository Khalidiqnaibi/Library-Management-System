package immortal.librarymanagementsystem.DTOs.Category;

import jakarta.validation.constraints.NotNull;

public class CategoryRequestDTO {
    @NotNull(message = "Category name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryRequestDTO() {
    }

    public CategoryRequestDTO(String name) {
        this.name = name;
    }
}
