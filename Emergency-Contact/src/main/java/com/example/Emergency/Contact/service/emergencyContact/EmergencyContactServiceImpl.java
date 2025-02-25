package com.example.Emergency.Contact.service.emergencyContact;

import com.example.Emergency.Contact.dao.EmergencyContactRepository;
import com.example.Emergency.Contact.model.EmergencyContact;
import com.example.Emergency.Contact.reftype.YNStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class EmergencyContactServiceImpl implements EmergencyContactService {
    @Autowired
    private EmergencyContactRepository emergencyContactRepository;
    @Override
    public EmergencyContact saveEmergencyContact (EmergencyContact emergencyContact,String currentUsername){
        emergencyContact.setCreatedBy(currentUsername);
        emergencyContact.setCreatedOn(LocalDateTime.now());
        return emergencyContactRepository.save(emergencyContact);
    }
    @Override
    public EmergencyContact updateEmergencyContact (EmergencyContact emergencyContact,String currentUsername){
        emergencyContact.setUpdatedBy(currentUsername);
        emergencyContact.setUpdatedOn(LocalDateTime.now());
        return emergencyContactRepository.save(emergencyContact);
    }
    @Override
    public EmergencyContact deleteEmergencyContact(Long emergencyContactId,String currentUsername){
        EmergencyContact emergencyContact = emergencyContactRepository.findByEmergencyContactId(emergencyContactId);
        emergencyContact.setUpdatedBy(currentUsername);
        emergencyContact.setDeleted(YNStatus.YES.getStatus());
        return emergencyContact;
    }
    @Override
    public EmergencyContact findByEmergencyContactById(Long emergencyContactId){
        return emergencyContactRepository.findByEmergencyContactId(emergencyContactId);
    }
    public List<EmergencyContact> listAllEmergencyContact() {
        return emergencyContactRepository.listAllEmergencyContact();
    }
}
