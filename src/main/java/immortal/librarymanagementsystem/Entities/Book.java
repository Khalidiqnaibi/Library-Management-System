package immortal.librarymanagementsystem.Entities;

import jakarta.persistence.*;

@Entity
public class Book {
    @Column(nullable = false)
    private String title;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    public Book(String title, Long id, Author author, Category category) {
        this.title = title;
        this.id = id;
        this.borrower = null;
        this.author = author;
        this.category = category;
    }

    public Book() {
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isBorrowed(){
        return this.borrower != null;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }
}
