package com.epam.chatgpt_task3.repository;

import com.epam.chatgpt_task3.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    Medication findByMedicationName(String medicationName);
}
