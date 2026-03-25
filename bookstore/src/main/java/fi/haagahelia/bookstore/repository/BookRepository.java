package fi.haagahelia.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.bookstore.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

	List<Book> findByTitle(String title);

}
