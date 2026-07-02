package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.Entities.Category;

public interface CategoryService {
    void createCategory();
    Category readCategory();
    void updateCategory();
    void deleteCategory();
}
