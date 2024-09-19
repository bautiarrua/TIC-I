package org.example.tici.Service;

import org.example.tici.Exceptions.UsernameNotFound;
import org.example.tici.Exceptions.WrongPassword;
import org.example.tici.Exceptions.YaExiste;
import org.example.tici.Model.Entities.Users;
import org.example.tici.Repository.UserRepository;
import org.example.tici.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public Users registerUser(Users user) throws YaExiste {
        if( userRepository.findByMail(user.getMail()) != null){
           throw new YaExiste();
        }
        String hashedPassword = passwordEncoder.encode(user.getPasword());
        user.setPasword(hashedPassword);
        userRepository.save(user);
        return user;
    }

    public Users loadUserByUsername(Users userLoad) throws UsernameNotFound{

        Users user = userRepository.findByUsername(userLoad.getName());
        if(user == null){
            throw new UsernameNotFound("Username not found");
        }

        if(Objects.equals(user.getPasword(), passwordEncoder.encode(userLoad.getPasword()))){
            //dar acceso a la pagina con usuario
            return user;
        }
        throw new WrongPassword("Wrong Password");
    }
}
