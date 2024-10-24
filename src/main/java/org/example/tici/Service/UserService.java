package org.example.tici.Service;

import org.example.tici.Exceptions.UsernameNotFound;
import org.example.tici.Exceptions.WrongPassword;
import org.example.tici.Exceptions.YaExiste;
import org.example.tici.Model.Entities.Users;
import org.example.tici.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public Users registerUser(Users user) throws YaExiste {
        if (user.getMail() == null || user.getMail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        if( userRepository.findByMail(user.getMail()) != null){
           throw new YaExiste();
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return user;
    }

    public Users loadUserByEmailAndPassword(String mail, String password) throws UsernameNotFound{

        if (mail == null || mail.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        Users user = userRepository.findByMail(mail);
        if(user == null){
            throw new UsernameNotFound("User not found");
        }

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new WrongPassword("Wrong Password");
        }
        return user;
    }
}
