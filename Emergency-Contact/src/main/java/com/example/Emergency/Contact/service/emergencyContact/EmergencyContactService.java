package com.example.Emergency.Contact.service.emergencyContact;

import com.example.Emergency.Contact.model.EmergencyContact;

import java.util.List;

public interface EmergencyContactService {
    EmergencyContact saveEmergencyContact (EmergencyContact emergencyContact,String currentUsername);
    EmergencyContact updateEmergencyContact (EmergencyContact emergencyContact,String currentUsername);
    EmergencyContact deleteEmergencyContact (Long emergencyContactId,String currentUsername);
    EmergencyContact findByEmergencyContactById (Long emergencyContactId);
    List<EmergencyContact> listAllEmergencyContact();
}
