package com.sac.sos.payload.request;

import com.sac.sos.model.AppUser;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequest {
    private Long id;
    private String name;
    private String username;
    private String contactNumber;
    private String password;

    public AppUser createEntity() {
        return AppUser.builder()
                .name(this.name)
                .username(this.username)
                .contactNumber(this.contactNumber)
                .password(this.password)
                .build();
    }

    public AppUser updateEntity(AppUser appUser) {
        appUser.setName(this.name);
        appUser.setUsername(this.username);
        appUser.setContactNumber(this.contactNumber);
        return appUser;
    }

}
