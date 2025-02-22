package com.example.Emergency.Contact.controller;

import com.example.Emergency.Contact.model.EmergencyContact;
import com.example.Emergency.Contact.payload.request.EmergencyContactRequest;
import com.example.Emergency.Contact.payload.response.EmergencyContactResponse;
import com.example.Emergency.Contact.service.emergencyContact.EmergencyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency_contact")
public class EmergencyContactController {
    @Autowired
    private EmergencyContactService emergencyContactService;

    @GetMapping
    public ResponseEntity<List<EmergencyContactResponse>> listAllEmergencyContact(){
        try{
            List<EmergencyContact> emergencyContactList = emergencyContactService.listAllEmergencyContact();
            List<EmergencyContactResponse> emergencyContactResponseList = EmergencyContactResponse.createResponseList(emergencyContactList);
            return new ResponseEntity<>(emergencyContactResponseList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmergencyContactResponse> getEmergencyContactById(@PathVariable("id") long id){
        try{
            EmergencyContact emergencyContact = emergencyContactService.findByEmergencyContactById(id);
            return new ResponseEntity<>(EmergencyContactResponse.createResponse(emergencyContact), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<EmergencyContactResponse> saveEmergencyContact(@RequestBody EmergencyContactRequest emergencyContactRequest, @RequestHeader(value = "Current-Username") String currentUsername) {
        try {
            EmergencyContact saveEmergencyContact = emergencyContactService.saveEmergencyContact(emergencyContactRequest.createEntity(),currentUsername);
            return new ResponseEntity<>(EmergencyContactResponse.createResponse(saveEmergencyContact), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<EmergencyContactResponse> updatedEmergencyContact(@RequestBody EmergencyContactRequest emergencyContactRequest, @RequestHeader(value = "Current-Username") String currentUsername){
        try {
            EmergencyContact emergencyContact = emergencyContactService.findByEmergencyContactById(emergencyContactRequest.getEmergencyContactId());
            EmergencyContact updateEmergencyContact = emergencyContactService.updateEmergencyContact(emergencyContactRequest.updateEntity(emergencyContact),currentUsername);
            return new ResponseEntity<>(EmergencyContactResponse.createResponse(updateEmergencyContact), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmergencyContactResponse> deleteEmergencyContact(@PathVariable("id") long id, @RequestHeader(value = "Current-Username") String currentUsername){
        try {
            EmergencyContact deleteEmergencyContact = emergencyContactService.deleteEmergencyContact(id,currentUsername);
            return new ResponseEntity<>(EmergencyContactResponse.createResponse(deleteEmergencyContact), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
