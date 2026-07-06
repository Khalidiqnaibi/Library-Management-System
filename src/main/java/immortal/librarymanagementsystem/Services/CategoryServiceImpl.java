package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Category.CategoryRequestDTO;
import immortal.librarymanagementsystem.DTOs.Category.CategoryResponseDTO;
import immortal.librarymanagementsystem.Entities.Borrower;
import immortal.librarymanagementsystem.Entities.Category;
import immortal.librarymanagementsystem.Exceptions.ResourceNotFoundException;
import immortal.librarymanagementsystem.Repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());

        Category savedCategory = categoryRepository.save(category);
        return ConvertToResponseDTO(savedCategory);
    }

    @Override
    public CategoryResponseDTO readCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category with ID: "+id+ " not found"));

        return ConvertToResponseDTO(category);
    }

    @Override
    public List<CategoryResponseDTO> readAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::ConvertToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Update failed.. no Borrower with ID: "+id));
        category.setName(categoryRequestDTO.getName());

        Category savedCategory = categoryRepository.save(category);
        return ConvertToResponseDTO(savedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Delete failed.. Category not found with ID: "+id));
        categoryRepository.delete(category);
    }

    public CategoryResponseDTO ConvertToResponseDTO(Category category){
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());

        return categoryResponseDTO;
    }
}
