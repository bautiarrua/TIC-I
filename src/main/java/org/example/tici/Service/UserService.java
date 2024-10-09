package org.example.tici.Service;

import org.example.tici.Exceptions.UsernameNotFound;
import org.example.tici.Exceptions.WrongPassword;
import org.example.tici.Exceptions.YaExiste;
import org.example.tici.Model.Entities.Users;
import org.example.tici.Repository.UserRepository;
import org.example.tici.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Objects;

@Service
public class UserService {
    @Autowired
    public BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    public Users registerUser(Users user) throws YaExiste {

        if( userRepository.findByMail(user.getMail()) != null){
            //logger.error(userRepository.findByMail(user.getMail()).getMail().toString());
           throw new YaExiste();
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return user;
    }

    public Users loadUserByUsername(Users userLoad) throws UsernameNotFound{

        Users user = userRepository.findByName(userLoad.getName());
        if(user == null){
            throw new UsernameNotFound("Username not found");
        }

        if(Objects.equals(user.getPassword(), passwordEncoder.encode(userLoad.getPassword()))){
            return user;
        }
        throw new WrongPassword("Wrong Password");
    }
}
