package com.pandacreep.onlineshop.domain.category;

import com.pandacreep.onlineshop.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Page<CategoryDTO> findAllCategories(Pageable pageable) {
        var categories = categoryRepository.findAll(pageable)
                .map(CategoryDTO::from);
        return categories;
    }

    public CategoryDTO getCategory(int id) {
        var category = categoryRepository
                .findById(id).orElseThrow( () -> new ResourceNotFoundException("category", id));
        return CategoryDTO.from(category);
    }
}
