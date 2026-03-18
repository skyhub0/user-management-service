package com.inspire.user.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "User")
@Getter
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private Long userId;

    private String name;
}