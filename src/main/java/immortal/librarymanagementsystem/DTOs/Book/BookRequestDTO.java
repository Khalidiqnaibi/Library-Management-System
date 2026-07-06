package immortal.librarymanagementsystem.DTOs.Book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BookRequestDTO {
    @NotBlank(message = "title is required")
    @Size(max = 100, message = "title must be under 100 characters")
    private String title;
    @NotNull(message = "Author ID is required")
    @Positive(message = "Author ID is not valid")
    private Long authorId;
    @NotNull(message = "Category ID is required")
    @Positive(message = "Category ID is not valid")
    private Long categoryId;

    public BookRequestDTO(String title, Long authorId, Long categoryId) {
        this.title = title;
        this.authorId = authorId;
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryID(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BookRequestDTO() {
    }
}
