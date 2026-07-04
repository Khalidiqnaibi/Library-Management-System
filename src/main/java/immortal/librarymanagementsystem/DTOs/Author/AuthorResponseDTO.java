package immortal.librarymanagementsystem.DTOs.Author;

public class AuthorResponseDTO {
    private Long id;
    private String name;

    public AuthorResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
