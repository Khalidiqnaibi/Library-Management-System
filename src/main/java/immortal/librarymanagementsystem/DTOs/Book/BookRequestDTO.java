package immortal.librarymanagementsystem.DTOs.Book;

import jakarta.validation.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BookRequestDTO {
    @NotNull(message = "title is required")
    private String title;
    @NotNull(message = "Author ID is required")
    @Positive(message = "Author ID is not valid")
    private Long authorId;
    @NotNull(message = "Category ID is required")
    @Positive(message = "Category ID is not valid")
    private Long categoryID;

    public BookRequestDTO(String title, Long authorId, Long categoryID) {
        this.title = title;
        this.authorId = authorId;
        this.categoryID = categoryID;
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

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public BookRequestDTO() {
    }
}
