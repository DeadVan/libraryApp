package com.dadvani.libraryApp.repository;

import com.dadvani.libraryApp.models.BorrowedBook;
import com.dadvani.libraryApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    List<User> findByRole(String role);
    Integer countById(Integer id);
}
