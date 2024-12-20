package org.example.tici.Repository;
import org.example.tici.Model.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByMail(String mail);
    Users findByName(String name);

    Users findByIdUs(Integer idUs);
}







