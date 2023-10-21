package com.epam.chatgpt_task3.controller;

import com.epam.chatgpt_task3.model.Medication;
import com.epam.chatgpt_task3.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medications")
public class MedicationController {

    @Autowired
    private MedicationRepository medicationRepository;

    @GetMapping
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Medication getMedicationById(@PathVariable Long id) {
        return medicationRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Medication createMedication(@RequestBody Medication medication) {
        return medicationRepository.save(medication);
    }

    @PutMapping("/{id}")
    public Medication updateMedication(@PathVariable Long id, @RequestBody Medication medication) {
        return medicationRepository.findById(id)
                                   .map(existingMedication -> {
                                       existingMedication.setMedicationName(medication.getMedicationName());
                                       existingMedication.setDosage(medication.getDosage());
                                       existingMedication.setDuration(medication.getDuration());
                                       return medicationRepository.save(existingMedication);
                                   })
                                   .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteMedication(@PathVariable Long id) {
        medicationRepository.deleteById(id);
    }
}
