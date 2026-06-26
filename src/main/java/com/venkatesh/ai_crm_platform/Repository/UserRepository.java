package com.venkatesh.ai_crm_platform.Repository;


import com.venkatesh.ai_crm_platform.models.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}