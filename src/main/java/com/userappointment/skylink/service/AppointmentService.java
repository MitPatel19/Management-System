package com.userappointment.skylink.service;

import com.userappointment.skylink.models.Appointment;
import com.userappointment.skylink.models.User;
import com.userappointment.skylink.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointment(User userDetails){
        if (userDetails.getRole().getRoleName().equals("Admin")){
            return appointmentRepository.findAll();
        }else{
//            return appointmentRepository.findAll();
            return appointmentRepository.findByUserEmail(userDetails.getEmail());
        }

    }

    public Optional<Appointment> getAppointment(Long id){
        return appointmentRepository.findById(id);
    }

    public Appointment createAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id){
        appointmentRepository.deleteById(id);
    }

    public Appointment updateAppointment(Appointment updatedAppointment){
        return appointmentRepository.save(updatedAppointment);
    }


}
