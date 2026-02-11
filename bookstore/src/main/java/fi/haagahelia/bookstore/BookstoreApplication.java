package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.model.Book;
import fi.haagahelia.bookstore.model.Category;
import fi.haagahelia.bookstore.repository.BookRepository;
import fi.haagahelia.bookstore.repository.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			Category fiction = categoryRepository.save(new Category("Fiction"));
			Category classics = categoryRepository.save(new Category("Classics"));
			Category dystopian = categoryRepository.save(new Category("Dystopian"));
			categoryRepository.save(new Category("Science Fiction"));
			categoryRepository.save(new Category("Fantasy"));
			categoryRepository.save(new Category("History"));
			categoryRepository.save(new Category("Biography"));
			categoryRepository.save(new Category("Mystery"));

			Book book1 = new Book("A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", 0.0);
			book1.setCategory(classics);
			bookRepository.save(book1);

			Book book2 = new Book("Animal Farm", "George Orwell", 1945, "2212343-5", 0.0);
			book2.setCategory(dystopian);
			bookRepository.save(book2);
		};
	}

}
