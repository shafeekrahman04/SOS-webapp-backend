package com.sac.sos.service.appUser;

import com.sac.sos.dao.AppUserRepository;
import com.sac.sos.model.AppUser;
import com.sac.sos.utils.enumConstant.YNStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.sac.sos.utils.Constant.CURRENT_USER_NAME;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<AppUser> listAllAppUser() {
        return appUserRepository.findAllByDeleted(YNStatus.NO.getStatus());
    }

    @Override
    public AppUser findAppUserById(Long appUserId) {
        return appUserRepository.findAppUserByIdAndDeleted(appUserId, YNStatus.NO.getStatus());
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setCreatedBy(CURRENT_USER_NAME);
        appUser.setCreatedOn(LocalDateTime.now());
        appUser.setDeleted(YNStatus.NO.getStatus());
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser updateAppUser(AppUser appUser) {
        appUser.setUpdatedBy(CURRENT_USER_NAME);
        appUser.setUpdatedOn(LocalDateTime.now());
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser deleteAppUser(Long appUserId) {
        AppUser appUser = appUserRepository.findAppUserByIdAndDeleted(appUserId, YNStatus.NO.getStatus());
        appUser.setUpdatedBy(CURRENT_USER_NAME);
        appUser.setDeleted(YNStatus.YES.getStatus());
        appUser.setUpdatedOn(LocalDateTime.now());
        return appUser;
    }


    @Override
    public Boolean existsByUsername(String username) {
        return appUserRepository.existsByUsername(username);
    }
}
