package com.sac.sos.controller;

import com.sac.sos.model.EmergencyContact;
import com.sac.sos.payload.ApiError;
import com.sac.sos.payload.ApiResponse;
import com.sac.sos.payload.request.EmergencyContactRequest;
import com.sac.sos.payload.response.EmergencyContactResponse;
import com.sac.sos.service.emergencyContactService.EmergencyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency-contact")
public class EmergencyContactController {
    @Autowired
    private EmergencyContactService emergencyContactService;

    @GetMapping("app-user/{appUserId}")
    public ResponseEntity<ApiResponse<List<EmergencyContactResponse>>> getAllEmergencyContact(@PathVariable("appUserId") Long appUserId) {
        try {
            List<EmergencyContact> emergencyContact = emergencyContactService.listAllEmergencyContactByAppUserId(appUserId);
            List<EmergencyContactResponse> emergencyContactResponses = EmergencyContactResponse.createResponseList(emergencyContact);
            return ResponseEntity.ok(new ApiResponse<>(true, emergencyContactResponses, null));
        } catch (Exception e) {
            ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, error));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmergencyContactResponse>> getEmergencyContactById(@PathVariable("id") long id) {
        try {
            EmergencyContact emergencyContact = emergencyContactService.findEmergencyContactById(id);
            if (emergencyContact == null) {
                ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, error));
            }
            EmergencyContactResponse emergencyContactResponse = EmergencyContactResponse.createResponse(emergencyContact);
            return ResponseEntity.ok(new ApiResponse<>(true, emergencyContactResponse, null));
        } catch (Exception e) {
            ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, error));
        }
    }


    @PostMapping
    public ResponseEntity<ApiResponse<EmergencyContactResponse>> saveEmergencyContact(@RequestBody EmergencyContactRequest emergencyContactRequest) {
        try {
            EmergencyContact saveEmergencyContact = emergencyContactService.saveEmergencyContact(emergencyContactRequest.createEntity());
            return ResponseEntity.ok(new ApiResponse<>(true, EmergencyContactResponse.createResponse(saveEmergencyContact), null));

        } catch (Exception e) {
            ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, error));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<EmergencyContactResponse>> updateEmergencyContact(@RequestBody EmergencyContactRequest emergencyContactRequest) {
        try {
            EmergencyContact emergencyContact = emergencyContactService.findEmergencyContactById(emergencyContactRequest.getId());
            if (emergencyContact == null) {
                ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, error));
            }
            EmergencyContact updateEmergencyContact = emergencyContactService.updateEmergencyContact(emergencyContactRequest.updateEntity(emergencyContact));
            return ResponseEntity.ok(new ApiResponse<>(true, EmergencyContactResponse.createResponse(updateEmergencyContact), null));
        } catch (Exception e) {
            ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, error));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmergencyContact(@PathVariable("id") long id) {
        try {
            EmergencyContact deleteEmergencyContact = emergencyContactService.deleteEmergencyContact(id);
            if (deleteEmergencyContact == null) {
                ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, error));
            }
            return ResponseEntity.ok(new ApiResponse<>(true, "EmergencyContact deleted successfully", null));
        } catch (Exception e) {
            ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, error));
        }
    }

}
