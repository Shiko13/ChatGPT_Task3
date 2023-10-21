package com.epam.chatgpt_task3.controller;

import com.epam.chatgpt_task3.model.Appointment;
import com.epam.chatgpt_task3.model.Patient;
import com.epam.chatgpt_task3.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    public void setUp() {
        // Optional: You can initialize the database with test data here if needed.
        // patientRepository.save(new Patient(...));
    }

    @Test
    public void testGetAllPatients() throws Exception {
        mockMvc.perform(get("/patients"))
               .andExpect(status().isOk());
    }

    @Test
    public void testGetPatientById() throws Exception {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(1L, LocalDateTime.now()));
        // Create a patient for testing
        Patient patient = new Patient(1L, "John Doe", appointments);
        patient = patientRepository.save(patient);

        mockMvc.perform(get("/patients/" + patient.getId()))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testCreatePatient() throws Exception {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(1L, LocalDateTime.now()));
        Patient patient = new Patient(1L, "Jane Doe", appointments);

        mockMvc.perform(post("/patients")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(patient)))
               .andExpect(status().isOk());

    }

    @Test
    public void testUpdatePatient() throws Exception {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(1L, LocalDateTime.now()));
        // Create a patient for testing
        Patient patient = new Patient(1L, "Alice", appointments);
        patient = patientRepository.save(patient);

        // Update the patient's name
        patient.setName("Alicia");

        mockMvc.perform(put("/patients/" + patient.getId())
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(patient)))
               .andExpect(status().isOk());

        // Optional: Verify that the patient was updated in the database
        Patient updatedPatient = patientRepository.findById(patient.getId()).orElse(null);
        assert updatedPatient != null;
        assert updatedPatient.getName().equals("Alicia");
    }

    @Test
    public void testDeletePatient() throws Exception {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(1L, LocalDateTime.now()));
        // Create a patient for testing
        Patient patient = new Patient(1L, "Bob", appointments);
        patient = patientRepository.save(patient);

        mockMvc.perform(delete("/patients/" + patient.getId()))
               .andExpect(status().isOk());

        // Optional: Verify that the patient was deleted from the database
        Patient deletedPatient = patientRepository.findById(patient.getId()).orElse(null);
        assert deletedPatient == null;
    }
}
