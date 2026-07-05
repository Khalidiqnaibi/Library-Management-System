package immortal.librarymanagementsystem.Controllers;


import immortal.librarymanagementsystem.Services.AuthorService;
import immortal.librarymanagementsystem.Services.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;




}
