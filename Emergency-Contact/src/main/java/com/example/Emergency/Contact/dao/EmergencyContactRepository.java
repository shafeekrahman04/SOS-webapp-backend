package com.example.Emergency.Contact.dao;

import com.example.Emergency.Contact.model.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact,Long> {
    @Query("select e from EmergencyContact e where e.deleted = 'F' order by e.id DESC")
    List<EmergencyContact> listAllEmergencyContact();
    EmergencyContact findByEmergencyContactId(Long emergencyContactId);
}
