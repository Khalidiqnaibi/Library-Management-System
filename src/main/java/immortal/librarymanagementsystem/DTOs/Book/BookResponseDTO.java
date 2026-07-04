package immortal.librarymanagementsystem.DTOs.Book;

import immortal.librarymanagementsystem.Entities.Author;
import immortal.librarymanagementsystem.Entities.Borrower;
import immortal.librarymanagementsystem.Entities.Category;

public class BookResponseDTO {
    private Long id;
    private String title;
    private boolean isBorrowed;
    private String authorName;
    private String categoryName;
    private String borrowerName;

    public BookResponseDTO() {
    }

    public BookResponseDTO(Long id, String title, boolean isBorrowed, String authorName, String categoryName, String borrowerName) {
        this.id = id;
        this.title = title;
        this.isBorrowed = isBorrowed;
        this.authorName = authorName;
        this.categoryName = categoryName;
        this.borrowerName = borrowerName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
