package com.epam.chatgpt_task3.repository;

import com.epam.chatgpt_task3.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findByAppointmentTime(LocalDateTime appointmentTime);
}
