package fi.haagahelia.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.bookstore.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findByName(String name);

}