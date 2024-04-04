package com.userappointment.skylink.controller;

import com.userappointment.skylink.configuration.SecurityConfig;
import com.userappointment.skylink.exception.CustomException;
import com.userappointment.skylink.models.Appointment;
import com.userappointment.skylink.models.User;
import com.userappointment.skylink.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private SecurityConfig securityConfig;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointment(@RequestHeader("Authorization") String authorizationHeader){
//        return appointmentService.getAllAppointment();
        User userDetails = securityConfig.GetUserDetails(authorizationHeader.replace("Basic ", ""));
        return ResponseEntity.ok(appointmentService.getAllAppointment(userDetails));
    }

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Appointment appointment) {
        try {
            User userDetails = securityConfig.GetUserDetails(authorizationHeader.replace("Basic ", ""));
            if (userDetails != null) {
                // User is authorized, perform the appointment creation logic
                Appointment createdAppointment = appointmentService.createAppointment(appointment);
                return ResponseEntity.ok(createdAppointment);
            } else {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Unauthorized");
                errorResponse.put("message", "You are not authorized to perform this action!");
                // User is not authorized
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", e.getMessage());
            // Handle any exceptions and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
    }

    @GetMapping("/{id}")
    public Optional<Appointment> getUserById(@PathVariable Long id){
        return appointmentService.getAppointment(id);
    }
}
