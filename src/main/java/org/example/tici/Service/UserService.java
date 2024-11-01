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
        if( userRepository.findByMail(user.getMail()) != null){
           throw new YaExiste();
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return user;
    }

    public Users loadUserByEmailAndPassword(String mail, String password) throws UsernameNotFound{
        
        Users user = userRepository.findByMail(mail);
        if(user == null){
            throw new UsernameNotFound("User not found");
        }

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new WrongPassword("Wrong Password");
        }
        System.out.println("______________________------------------------Se entro______________________------------------------");
        return user;
    }
}
