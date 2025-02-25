package com.sac.sos.dao;

import com.sac.sos.model.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {

    List<EmergencyContact> findAllByAppUserIdAndDeleted(Long appUserId, String deleted);

    EmergencyContact findByIdAndDeleted(Long id, String deleted);
}
