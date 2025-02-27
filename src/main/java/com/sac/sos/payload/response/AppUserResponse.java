package com.sac.sos.payload.response;

import com.sac.sos.model.AppUser;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponse {

    private Long id;
    private String name;
    private String username;
    private String contactNumber;
    private String email;

    public static AppUserResponse createResponse(AppUser appUser) {
        return AppUserResponse.builder()
                .id(appUser.getId())
                .name(appUser.getName())
                .username(appUser.getUsername())
                .contactNumber(appUser.getContactNumber())
                .email(appUser.getEmail())
                .build();
    }

    public static List<AppUserResponse> createResponseList(List<AppUser> list) {
        return list.stream().map(AppUserResponse::createResponse).collect(Collectors.toList());
    }

}
