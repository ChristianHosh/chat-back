package com.chat.chatback.user;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_user", indexes = {
        @Index(name = "idx_user_username_unq", columnList = "username", unique = true)
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_user_username", columnNames = {"username"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "display_name")
    private String displayName;

    @Enumerated
    @Column(name = "role", nullable = false)
    private UserRole role = UserRole.ROLE_USER;

}