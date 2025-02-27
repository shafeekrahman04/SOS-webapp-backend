package com.sac.sos.payload.response;

import com.sac.sos.model.AppUser;
import com.sac.sos.model.EmergencyContact;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyContactResponse {

    private Long id;
    private String name;
    private String contactNumber;
    private Long appUserId;
    private String email;

    public static EmergencyContactResponse createResponse(EmergencyContact emergencyContact) {
        return EmergencyContactResponse.builder()
                .id(emergencyContact.getId())
                .name(emergencyContact.getName())
                .contactNumber(emergencyContact.getContactNumber())
                .appUserId(emergencyContact.getAppUserId())
                .email(emergencyContact.getEmail())
                .build();
    }

    public static List<EmergencyContactResponse> createResponseList(List<EmergencyContact> list) {
        return list.stream().map(EmergencyContactResponse::createResponse).collect(Collectors.toList());
    }
}
