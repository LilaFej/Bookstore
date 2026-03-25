package fi.haagahelia.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.bookstore.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
