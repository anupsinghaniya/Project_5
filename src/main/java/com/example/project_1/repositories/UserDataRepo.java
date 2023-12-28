package com.example.project_1.repositories;

import com.example.project_1.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepo extends JpaRepository<UserData, String> {
}
