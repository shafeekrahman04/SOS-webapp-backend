package com.sac.sos.controller;

import com.sac.sos.model.EmergencyContact;
import com.sac.sos.payload.ApiError;
import com.sac.sos.payload.ApiResponse;
import com.sac.sos.payload.response.EmergencyContactResponse;
import com.sac.sos.service.emergencyContactService.EmergencyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/emergency-contact")
public class EmergencyContactController {
    @Autowired
    private EmergencyContactService emergencyContactService;

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<List<EmergencyContactResponse>>> getAllEmergencyContact(@PathVariable("id") Long id) {
        try {
            List<EmergencyContact> emergencyContact = emergencyContactService.listAllEmergencyContactByAppUserId(id);
            List<EmergencyContactResponse> emergencyContactResponses = EmergencyContactResponse.createResponseList(emergencyContact);
            return ResponseEntity.ok(new ApiResponse<>(true, emergencyContactResponses, null));
        } catch (Exception e) {
            ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, error));
        }
    }

}
