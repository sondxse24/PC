package com.pc.model;

import com.pc.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Nationalized
    @Column(name = "name", nullable = false)
    private String name;

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

    public Users(String name, String password, String phoneNumber, String email, String address, Role role, LocalDateTime createAt) {
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.role = role;
        this.createAt = createAt;
    }
}
