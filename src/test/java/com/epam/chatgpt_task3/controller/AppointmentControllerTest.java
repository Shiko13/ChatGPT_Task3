package com.epam.chatgpt_task3.controller;
import com.epam.chatgpt_task3.model.Appointment;
import com.epam.chatgpt_task3.repository.AppointmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void setUp() {
        // Optional: You can initialize the database with test data here if needed.
        // appointmentRepository.save(new Appointment(...));
    }

    @Test
    public void testGetAllAppointments() throws Exception {
        mockMvc.perform(get("/appointments"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetAppointmentById() throws Exception {
        // Create an appointment for testing
        Appointment appointment = new Appointment(1L, LocalDateTime.now());

        appointment = appointmentRepository.save(appointment);

        mockMvc.perform(get("/appointments/" + appointment.getId()))
               .andExpect(status().isOk());
    }

    @Test
    public void testCreateAppointment() throws Exception {
        Appointment appointment = new Appointment(1L, LocalDateTime.now());

        mockMvc.perform(post("/appointments")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(appointment)))
               .andExpect(status().isOk());

    }

    @Test
    public void testUpdateAppointment() throws Exception {
        // Create an appointment for testing
        Appointment appointment = new Appointment(1L, LocalDateTime.now());
        appointment = appointmentRepository.save(appointment);

        // Update the appointment time
        appointment.setAppointmentTime(LocalDateTime.now());

        mockMvc.perform(put("/appointments/" + appointment.getId())
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(appointment)))
               .andExpect(status().isOk());

    }

    @Test
    public void testDeleteAppointment() throws Exception {
        // Create an appointment for testing
        Appointment appointment = new Appointment(1L, LocalDateTime.now());
        appointment = appointmentRepository.save(appointment);

        mockMvc.perform(delete("/appointments/" + appointment.getId())).andExpect(status().isOk());
    }
}
