package dev.abdelrahman.movies.Models.User;

import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    private ObjectId id;
    private String fitstName;
    private String lastName;
    private String username;
    private String email;
    private String Phone;
    private String password;
    private Role role;
    private Gender gender;
    private boolean logined;
    private boolean enabled = true;
    private boolean isActive = true;
    private boolean isLocked = false;

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.logined;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}