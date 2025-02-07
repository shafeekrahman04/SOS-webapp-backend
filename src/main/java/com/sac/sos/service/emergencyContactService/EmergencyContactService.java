package com.sac.sos.service.emergencyContactService;

import com.sac.sos.model.EmergencyContact;

import java.util.List;

public interface EmergencyContactService {
    List<EmergencyContact> listAllEmergencyContactByAppUserId(Long id);


}
