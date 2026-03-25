package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fi.haagahelia.bookstore.model.Book;
import fi.haagahelia.bookstore.model.Category;
import fi.haagahelia.bookstore.repository.BookRepository;
import fi.haagahelia.bookstore.repository.CategoryRepository;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByTitleShouldReturnBook() {
        Category category = categoryRepository.findByName("Classics").get(0);
        Book book = new Book("Demian", "Hermann Hesse", 1919, "ISBN-DEMIAN-001", 11.90);
        book.setCategory(category);
        bookRepository.save(book);

        List<Book> books = bookRepository.findByTitle("Demian");

        assertThat(books).isNotEmpty();
        assertThat(books).extracting(Book::getAuthor).contains("Hermann Hesse");
    }

    @Test
    public void createNewBook() {
        Category category = categoryRepository.findByName("Classics").get(0);
        Book book = new Book("The Ones Who Walk Away from Omelas", "Ursula K. Le Guin", 1973, "ISBN-OMELAS-001", 13.50);
        book.setCategory(category);
        bookRepository.save(book);

        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteNewBook() {
        Category category = categoryRepository.findByName("Classics").get(0);

        List<Book> existingBooks = bookRepository.findByTitle("The Ones Who Walk Away from Omelas");
        bookRepository.deleteAll(existingBooks);

        Book book = new Book("The Ones Who Walk Away from Omelas", "Ursula K. Le Guin", 1973, "ISBN-OMELAS-DELETE", 13.50);
        book.setCategory(category);
        bookRepository.save(book);

        List<Book> books = bookRepository.findByTitle("The Ones Who Walk Away from Omelas");
        Book foundBook = books.get(0);
        bookRepository.delete(foundBook);

        List<Book> newBooks = bookRepository.findByTitle("The Ones Who Walk Away from Omelas");
        assertThat(newBooks).isEmpty();
    }
}
