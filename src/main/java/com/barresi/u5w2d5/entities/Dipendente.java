package com.barresi.u5w2d5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Setter
@Getter

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dipendente implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String avatar;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "dipendente")
    private List<Dispositivo> lista_dispositivi;

    public Dipendente(String username,String nome,String cognome,String email){
        this.username= username;
        this.nome= nome;
        this.cognome= cognome;
        this.email = email;
        this.role = Role.USER;
    }

    @Override
    public String toString() {
        return "Dipendente{" +
                "username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
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