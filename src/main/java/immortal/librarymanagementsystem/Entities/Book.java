package immortal.librarymanagementsystem.Entities;

public class Book {
    private String name;
    private int id;
    private boolean isBorrowed;
    private Author author;
    private Category category;

    public Book(String name, int id, Author author, Category category) {
        this.name = name;
        this.isBorrowed = false;
        this.id = id;
        this.author = author;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
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

    public void Borrow(){
        if (this.isBorrowed){
            return;
        }
        this.isBorrowed = true;
    }
}
