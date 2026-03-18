package com.inspire.user.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "Auth")
@Getter
public class AuthEntity {
    @Id
    @Column(name = "user_id")
    private Long userId;

    private String email;
    private String password;
}
