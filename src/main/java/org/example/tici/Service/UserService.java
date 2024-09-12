package org.example.tici.Service;

import org.example.tici.Exceptions.YaExiste;
import org.example.tici.Model.Entities.Users;
import org.example.tici.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users registerUser(Users user) throws YaExiste {
        if( userRepository.findByMail(user.getMail()) != null){
           throw new YaExiste();
        }
        userRepository.save(user);
        return user;
    }
}
