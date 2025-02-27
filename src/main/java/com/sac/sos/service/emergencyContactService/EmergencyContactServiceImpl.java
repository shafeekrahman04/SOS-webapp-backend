package com.sac.sos.service.emergencyContactService;

import com.sac.sos.dao.EmergencyContactRepository;
import com.sac.sos.model.EmergencyContact;
import com.sac.sos.utils.enumConstant.YNStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.sac.sos.utils.Constant.CURRENT_USER_NAME;

@Service
@Transactional
public class EmergencyContactServiceImpl implements EmergencyContactService {

    @Autowired
    private EmergencyContactRepository emergencyContactRepository;

    @Override
    public List<EmergencyContact> listAllEmergencyContactByAppUserId(Long appUserId) {
        return emergencyContactRepository.findAllByAppUserIdAndDeleted(appUserId, YNStatus.NO.getStatus());
    }

    @Override
    public EmergencyContact saveEmergencyContact(EmergencyContact emergencyContact) {
        emergencyContact.setCreatedBy(CURRENT_USER_NAME);
        emergencyContact.setCreatedOn(LocalDateTime.now());
        emergencyContact.setDeleted(YNStatus.NO.getStatus());
        return emergencyContactRepository.save(emergencyContact);
    }

    @Override
    public EmergencyContact findEmergencyContactById(Long id) {
        return emergencyContactRepository.findByIdAndDeleted(id, YNStatus.NO.getStatus());
    }

    @Override
    public EmergencyContact updateEmergencyContact(EmergencyContact emergencyContact) {
        emergencyContact.setUpdatedBy(CURRENT_USER_NAME);
        emergencyContact.setUpdatedOn(LocalDateTime.now());
        return emergencyContactRepository.save(emergencyContact);
    }

    @Override
    public EmergencyContact deleteEmergencyContact(Long id) {
        EmergencyContact emergencyContact = emergencyContactRepository.findByIdAndDeleted(id, YNStatus.NO.getStatus());
        emergencyContact.setUpdatedBy(CURRENT_USER_NAME);
        emergencyContact.setDeleted(YNStatus.YES.getStatus());
        emergencyContact.setUpdatedOn(LocalDateTime.now());
        return emergencyContact;
    }
}
