package com.example.comicwebbe.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.comicwebbe.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    Optional<User> findById(Long id);
    @Query("SELECT u FROM User u WHERE u.username = :username and u.password = :password")
    Optional<User> findByUsernameAndPassword(String username, String password);
    void deleteById(Long id);
}
