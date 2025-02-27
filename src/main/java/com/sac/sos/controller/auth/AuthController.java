package com.sac.sos.controller.auth;


import com.sac.sos.model.AppUser;
import com.sac.sos.payload.ApiError;
import com.sac.sos.payload.ApiResponse;
import com.sac.sos.payload.request.AppUserRequest;
import com.sac.sos.payload.request.AuthRequest;
import com.sac.sos.payload.response.AppUserResponse;
import com.sac.sos.payload.response.AuthResponse;
import com.sac.sos.service.appUser.AppUserService;
import com.sac.sos.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authenticate")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserService appUserService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
        logger.info("Login to username : {}", authRequest.getUsername());

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthResponse.createResponse(null, true, null));
        }
        String token = jwtUtil.generateToken(authRequest.getUsername());
        AppUser loggedInUser = appUserService.findAppUserByUsername(authRequest.getUsername());
        AppUserResponse userResponse = AppUserResponse.createResponse(loggedInUser);
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.AUTHORIZATION, token).body(AuthResponse.createResponse(token, false, userResponse));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AppUserResponse>> register(@RequestBody AppUserRequest appUserRequest) {
        logger.info("Register to username : {}", appUserRequest.getUsername());

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
}
