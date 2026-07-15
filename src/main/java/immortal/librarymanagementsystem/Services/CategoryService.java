package immortal.librarymanagementsystem.Services;


import immortal.librarymanagementsystem.DTOs.Category.CategoryRequestDTO;
import immortal.librarymanagementsystem.DTOs.Category.CategoryResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO readCategory(Long id);
    Page<CategoryResponseDTO> readAllCategories(int page , int size);
    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO);
    void deleteCategory(Long id);
}
