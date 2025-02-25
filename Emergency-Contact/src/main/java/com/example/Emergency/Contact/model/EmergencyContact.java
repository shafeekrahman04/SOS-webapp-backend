package com.example.Emergency.Contact.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "emergency_contact")
public class EmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long emergencyContactId;

    @Column(name = "app_user_id")
    private Long appUserId;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "alternate_mobile")
    private String alternateMobile;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "deleted")
    private String deleted;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
}
