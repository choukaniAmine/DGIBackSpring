package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Notification;
import com.example.demo.entities.Reclamation;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
			List<Notification> findByReclamation(Reclamation reclamation);
}
