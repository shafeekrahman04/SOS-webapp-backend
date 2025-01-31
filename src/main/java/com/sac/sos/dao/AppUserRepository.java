package com.sac.sos.dao;

import com.sac.sos.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findAllByDeleted(String deleted);

    AppUser findAppUserByIdAndDeleted(Long appUserId, String deleted);

    Boolean existsByUsername(String username);
}
