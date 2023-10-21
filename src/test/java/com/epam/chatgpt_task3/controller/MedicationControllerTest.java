package com.epam.chatgpt_task3.controller;

import com.epam.chatgpt_task3.model.Medication;
import com.epam.chatgpt_task3.repository.MedicationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MedicationRepository medicationRepository;

    @BeforeEach
    public void setUp() {
        // Optional: You can initialize the database with test data here if needed.
        // medicationRepository.save(new Medication(...));
    }

    @Test
    public void testGetAllMedications() throws Exception {
        mockMvc.perform(get("/medications"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetMedicationById() throws Exception {
        // Create a medication for testing
        Medication medication = new Medication(1L, "Aspirin", "10 mg", "7 days");

        medication = medicationRepository.save(medication);

        mockMvc.perform(get("/medications/" + medication.getId()))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.medicationName").value("Aspirin"));
    }

    @Test
    public void testCreateMedication() throws Exception {
        Medication medication = new Medication(1L, "Ibuprofen", "15 mg", "14 days");

        mockMvc.perform(post("/medications")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(medication)))
               .andExpect(status().isOk());

        // Optional: Verify that the medication was saved to the database
        Medication savedMedication = medicationRepository.findByMedicationName("Ibuprofen");
        assert savedMedication != null;
    }

    @Test
    public void testUpdateMedication() throws Exception {
        // Create a medication for testing
        Medication medication = new Medication(1L, "Paracetamol", "20 mg", "5 days");
        medication = medicationRepository.save(medication);

        // Update the dosage
        medication.setDosage("25 mg");

        mockMvc.perform(put("/medications/" + medication.getId())
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(medication)))
               .andExpect(status().isOk());

        // Optional: Verify that the medication was updated in the database
        Medication updatedMedication = medicationRepository.findById(medication.getId()).orElse(null);
        assert updatedMedication != null;
        assert updatedMedication.getDosage().equals("25 mg");
    }

    @Test
    public void testDeleteMedication() throws Exception {
        // Create a medication for testing
        Medication medication = new Medication(1L, "Omeprazole", "40 mg", "30 days");
        medication = medicationRepository.save(medication);

        mockMvc.perform(delete("/medications/" + medication.getId()))
               .andExpect(status().isOk());

        // Optional: Verify that the medication was deleted from the database
        Medication deletedMedication = medicationRepository.findById(medication.getId()).orElse(null);
        assert deletedMedication == null;
    }
}
