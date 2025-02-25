package com.example.Emergency.Contact.payload.response;

import com.example.Emergency.Contact.model.EmergencyContact;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyContactResponse {
    private Long emergencyContactId;
    private Long appUserId;
    private String contactName;
    private String mobileNumber;
    private String alternateMobile;
    private String email;
    private String address;

    public static EmergencyContactResponse createResponse(EmergencyContact emergencyContact){
        return EmergencyContactResponse.builder()
                .emergencyContactId(emergencyContact.getEmergencyContactId())
                .appUserId(emergencyContact.getAppUserId())
                .contactName(emergencyContact.getContactName())
                .mobileNumber(emergencyContact.getMobileNumber())
                .alternateMobile(emergencyContact.getAlternateMobile())
                .email(emergencyContact.getEmail())
                .address(emergencyContact.getAddress())
                .build();
    }

    public static List<EmergencyContactResponse> createResponseList(List<EmergencyContact> list) {
        return list.stream().map(EmergencyContactResponse::createResponse).collect(Collectors.toList());
    }
}
