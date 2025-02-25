package com.example.Emergency.Contact.payload.request;

import com.example.Emergency.Contact.model.EmergencyContact;
import com.example.Emergency.Contact.reftype.YNStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyContactRequest {
    private Long emergencyContactId;
    private Long appUserId;
    private String contactName;
    private String mobileNumber;
    private String alternateMobile;
    private String email;
    private String address;

    public EmergencyContact createEntity(){
        return EmergencyContact.builder()
                .emergencyContactId(this.emergencyContactId)
                .appUserId(this.appUserId)
                .contactName(this.contactName)
                .mobileNumber(this.mobileNumber)
                .alternateMobile(this.alternateMobile)
                .email(this.email)
                .address(this.address)
                .deleted(YNStatus.NO.getStatus())
                .build();
    }

    public EmergencyContact updateEntity(EmergencyContact emergencyContact) {
        emergencyContact.setAppUserId(this.appUserId);
        emergencyContact.setContactName(this.contactName);
        emergencyContact.setMobileNumber(this.mobileNumber);
        emergencyContact.setAlternateMobile(this.alternateMobile);
        emergencyContact.setEmail(this.email);
        emergencyContact.setAddress(this.address);
        return emergencyContact;
    }
}
