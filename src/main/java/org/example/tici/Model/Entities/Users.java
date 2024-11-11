package org.example.tici.Model.Entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class Users implements UserDetails {
    @Id
    @Column(name = "id")
    private Integer idUs;

    @Column(name = "name", nullable = false, length = 100)
    private String name;


    @Column(name = "mail", nullable = false, length = 100)
    private String mail;

    @Column(name = "password",nullable = false, length = 100)
    private String password;

    public Users() {
    }
    public Users(String name, String mail, String password, String ageRating){
        this.name = name;
        this.mail = mail;
        this.password = password;
    }

    public Integer getIdUs() {
        return idUs;
    }

    public void setIdUs(Integer idUs) {
        this.idUs = idUs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Puedes agregar roles si los necesitas, por ahora, retorna una lista vacía.
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
