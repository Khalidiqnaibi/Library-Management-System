package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.Entities.Category;

public interface CategoryService {
    void createCategory(Long id, String name);
    Category readCategory(Long id);
    void updateCategory(Long id, Category category);
    void deleteCategory(Long id);
}
