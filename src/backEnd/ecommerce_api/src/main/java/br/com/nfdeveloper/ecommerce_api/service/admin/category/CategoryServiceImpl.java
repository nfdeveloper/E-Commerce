package br.com.nfdeveloper.ecommerce_api.service.admin.category;

import org.springframework.stereotype.Service;

import br.com.nfdeveloper.ecommerce_api.dto.CategoryDTO;
import br.com.nfdeveloper.ecommerce_api.entity.Category;
import br.com.nfdeveloper.ecommerce_api.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
			this.categoryRepository = categoryRepository;
	}
	
	public Category createCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setName(categoryDTO.getName());
		category.setDescription(categoryDTO.getDescription());
		
		return categoryRepository.save(category);
	}
	
}
