package br.com.nfdeveloper.ecommerce_api.service.admin.category;

import br.com.nfdeveloper.ecommerce_api.dto.CategoryDTO;
import br.com.nfdeveloper.ecommerce_api.entity.Category;

public interface CategoryService {

	Category createCategory(CategoryDTO categoryDTO);
}
