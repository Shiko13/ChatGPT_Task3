package com.epam.chatgpt_task3.controller;

import com.epam.chatgpt_task3.model.Appointment;
import com.epam.chatgpt_task3.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        return appointmentRepository.findById(id)
                                    .map(existingAppointment -> {
                                        existingAppointment.setAppointmentTime(appointment.getAppointmentTime());
                                        existingAppointment.setPatient(appointment.getPatient());
                                        return appointmentRepository.save(existingAppointment);
                                    })
                                    .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
    }
}
