package com.wowcreations.roupaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import com.wowcreations.roupaapi.entities.CustomUser;
import java.util.UUID;

public interface UserRepository extends JpaRepository<CustomUser, UUID> {
    UserDetails findByUsername(String username);
}
