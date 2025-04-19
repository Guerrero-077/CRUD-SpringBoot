package com.sena.TaskManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.TaskManagement.DTOs.RequestRegisterReminders;
import com.sena.TaskManagement.Service.RemindersServices;
import com.sena.TaskManagement.model.Reminders;

@RestController
@RequestMapping("api/v1/reminders")
public class RemindersController {

    @Autowired
    private RemindersServices remindersServices;

    @GetMapping("/")
    public ResponseEntity<Object> findAllReminders() {
        var listTasks = remindersServices.findAllReminders();
        return new ResponseEntity<>(listTasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdReminders(@PathVariable int id) {
        var categorie = remindersServices.findByIdReminders(id);
        return new ResponseEntity<>(categorie, HttpStatus.OK);
    }

    // ======================
    // = Create Reminders =
    // ======================
    @PostMapping("/crear")
    public ResponseEntity<Reminders> createReminders(@RequestBody RequestRegisterReminders request) {
        Reminders createdHistory = remindersServices.creaReminders(request);
        return ResponseEntity.ok(createdHistory);
    }

    // ======================
    // = Update Reminders =
    // ======================
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReminders(@PathVariable int id, @RequestBody Reminders remindersUpdate) {
        try {
            remindersServices.updateReminders(id, remindersUpdate);
            return ResponseEntity.ok("Reminders updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update Reminders: " + e.getMessage());
        }
    }
}
