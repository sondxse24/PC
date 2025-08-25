package com.laptop.model;

import com.laptop.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Nationalized
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "email",  nullable = false, unique = true)
    @Email
    private String email;

    @Nationalized
    @Column(name = "address",  nullable = false)
    private String address;

    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    public Users(String name, String username, String password, String phoneNumber, String email, String address, Role role, LocalDateTime createAt) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.role = role;
        this.createAt = createAt;
    }
}
