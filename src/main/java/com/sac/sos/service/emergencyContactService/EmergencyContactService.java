package com.sac.sos.service.emergencyContactService;

import com.sac.sos.model.EmergencyContact;

import java.util.List;

public interface EmergencyContactService {
    List<EmergencyContact> listAllEmergencyContactByAppUserId(Long appUserId);

    EmergencyContact saveEmergencyContact(EmergencyContact emergencyContact);

    EmergencyContact findEmergencyContactById(Long id);

    EmergencyContact updateEmergencyContact(EmergencyContact emergencyContact);

    EmergencyContact deleteEmergencyContact(Long id);


}
