package com.inspire.user.dao;

import com.inspire.userservice.mypage.domain.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthDao extends JpaRepository<AuthEntity, Long> {}