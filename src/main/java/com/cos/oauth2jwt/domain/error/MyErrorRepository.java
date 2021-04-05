package com.cos.oauth2jwt.domain.error;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyErrorRepository extends JpaRepository<MyError, Long> {

}
