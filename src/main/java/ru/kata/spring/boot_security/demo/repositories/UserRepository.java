package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserRepository  extends JpaRepository<User, Long> {
    @Query("Select t from User t join fetch t.roles where t.name =:name")
    User findByName(@PathVariable("name") String name);

}
