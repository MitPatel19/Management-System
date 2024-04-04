package com.userappointment.skylink.models;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "appointment")

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Date appointmentDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.EAGER)
    private User createdBy;
    @PrePersist
    protected  void onCreate(){
        createdAt = LocalDateTime.now();
    }

}
