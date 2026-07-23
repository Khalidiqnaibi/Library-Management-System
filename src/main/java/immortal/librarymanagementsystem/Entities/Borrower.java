package immortal.librarymanagementsystem.Entities;

import jakarta.persistence.*;

@Entity
public class Borrower {
    @Column(nullable = false)
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Borrower(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public Borrower() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
