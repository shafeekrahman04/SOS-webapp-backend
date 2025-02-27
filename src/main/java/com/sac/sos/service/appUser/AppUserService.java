package com.sac.sos.service.appUser;

import com.sac.sos.model.AppUser;

import java.util.List;

public interface AppUserService {
    List<AppUser> listAllAppUser();

    AppUser findAppUserById(Long appUserId);

    AppUser saveAppUser(AppUser appUser);

    AppUser updateAppUser(AppUser appUser);

    AppUser deleteAppUser(Long appUserId);

    Boolean existsByUsername(String username);

    AppUser findAppUserByUsername(String username);

}
