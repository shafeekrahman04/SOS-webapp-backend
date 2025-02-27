package com.sac.sos.payload.request;

import com.sac.sos.model.EmergencyContact;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyContactRequest {
    private Long id;
    private Long appUserId;
    private String name;
    private String email;
    private String contactNumber;

    public EmergencyContact createEntity() {
        return EmergencyContact.builder()
                .name(this.name)
                .appUserId(this.appUserId)
                .contactNumber(this.contactNumber)
                .email(this.email)
                .build();
    }

    public EmergencyContact updateEntity(EmergencyContact emergencyContact) {
        emergencyContact.setName(this.name);
        emergencyContact.setAppUserId(this.appUserId);
        emergencyContact.setContactNumber(this.contactNumber);
        emergencyContact.setEmail(this.email);
        return emergencyContact;
    }
}
