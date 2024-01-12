package br.com.nfdeveloper.ecommerce_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nfdeveloper.ecommerce_api.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
