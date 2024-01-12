package br.com.nfdeveloper.ecommerce_api.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nfdeveloper.ecommerce_api.dto.CategoryDTO;
import br.com.nfdeveloper.ecommerce_api.entity.Category;
import br.com.nfdeveloper.ecommerce_api.service.admin.category.CategoryService;

@RestController
@RequestMapping("/api/admin")
public class AdminCategoryController {

	private final CategoryService categoryService;

	public AdminCategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@PostMapping("category")
	public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO){
		Category category = categoryService.createCategory(categoryDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	
	
}
