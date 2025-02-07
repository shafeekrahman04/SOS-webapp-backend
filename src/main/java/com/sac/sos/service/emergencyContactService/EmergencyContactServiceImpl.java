package com.sac.sos.service.emergencyContactService;

import com.sac.sos.dao.EmergencyContactRepository;
import com.sac.sos.model.EmergencyContact;
import com.sac.sos.utils.enumConstant.YNStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmergencyContactServiceImpl implements EmergencyContactService {

    @Autowired
    private EmergencyContactRepository emergencyContactRepository;

    @Override
    public List<EmergencyContact> listAllEmergencyContactByAppUserId(Long id) {
        return emergencyContactRepository.findAllByAppUserIdAndDeleted(id,YNStatus.NO.getStatus());
    }
}
