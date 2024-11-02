package org.example.tici.Repository;

import org.example.tici.Model.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepositoryAuth extends JpaRepository<Users, Integer> {
    Optional<Users> findByMail(String email);
}
