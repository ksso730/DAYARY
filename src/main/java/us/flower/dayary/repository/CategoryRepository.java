package us.flower.dayary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	Category findBySubject(String subject);
	
}
