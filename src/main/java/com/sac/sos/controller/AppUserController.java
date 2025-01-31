package com.sac.sos.controller;

import com.sac.sos.model.AppUser;
import com.sac.sos.payload.ApiError;
import com.sac.sos.payload.ApiResponse;
import com.sac.sos.payload.request.AppUserRequest;
import com.sac.sos.payload.response.AppUserResponse;
import com.sac.sos.service.appUser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app-user")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AppUserResponse>>> getAllAppUser() {
        try {
            List<AppUser> appUserList = appUserService.listAllAppUser();
            List<AppUserResponse> appUserResponseList = AppUserResponse.createResponseList(appUserList);
            return ResponseEntity.ok(new ApiResponse<>(true, appUserResponseList, null));
        } catch (Exception e) {
            ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, error));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AppUserResponse>> getAppUserById(@PathVariable("id") long id) {
        try {
            AppUser appUser = appUserService.findAppUserById(id);
            if (appUser == null) {
                ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, error));
            }
            AppUserResponse appUserResponse = AppUserResponse.createResponse(appUser);
            return ResponseEntity.ok(new ApiResponse<>(true, appUserResponse, null));
        } catch (Exception e) {
            ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, error));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AppUserResponse>> saveAppUser(@RequestBody AppUserRequest appUserRequest) {
        try {
            Boolean usernameExists = appUserService.existsByUsername(appUserRequest.getUsername());
            if (usernameExists) {
                ApiError error = new ApiError(HttpStatus.CONFLICT.value(), "Username already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(false, null, error));
            }
            AppUser saveAppUser = appUserService.saveAppUser(appUserRequest.createEntity());
            return ResponseEntity.ok(new ApiResponse<>(true, AppUserResponse.createResponse(saveAppUser), null));

        } catch (Exception e) {
            ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, error));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<AppUserResponse>> updateAppUser(@RequestBody AppUserRequest appUserRequest) {
        try {
            AppUser appUser = appUserService.findAppUserById(appUserRequest.getId());
            if (appUser == null) {
                ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, error));
            }
            AppUser updateAppUser = appUserService.updateAppUser(appUserRequest.updateEntity(appUser));
            return ResponseEntity.ok(new ApiResponse<>(true, AppUserResponse.createResponse(updateAppUser), null));
        } catch (Exception e) {
            ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, error));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAppUser(@PathVariable("id") long id) {
        try {
            AppUser deleteAppUser = appUserService.deleteAppUser(id);
            if (deleteAppUser == null) {
                ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, error));
            }
            return ResponseEntity.ok(new ApiResponse<>(true, "AppUser deleted successfully", null));
        } catch (Exception e) {
            ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, error));
        }
    }

}
