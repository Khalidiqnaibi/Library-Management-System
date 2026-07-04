package immortal.librarymanagementsystem.Services;


import immortal.librarymanagementsystem.DTOs.Category.CategoryRequestDTO;
import immortal.librarymanagementsystem.DTOs.Category.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO readCategory(Long id);
    List<CategoryResponseDTO> readAllCategories();
    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO);
    void deleteCategory(Long id);
}
