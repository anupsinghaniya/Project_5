package com.example.project_1.repositories;

import com.example.project_1.models.ContactData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDataRepo extends JpaRepository<ContactData, Integer> {
}
