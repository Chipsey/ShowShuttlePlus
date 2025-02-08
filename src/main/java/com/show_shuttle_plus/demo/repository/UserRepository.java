package com.show_shuttle_plus.demo.repository;

import com.show_shuttle_plus.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
