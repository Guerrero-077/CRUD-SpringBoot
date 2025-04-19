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

import com.sena.TaskManagement.DTOs.RequestRegisterHistory;
import com.sena.TaskManagement.Service.HistoryServices;
import com.sena.TaskManagement.model.History;

@RestController
@RequestMapping("api/v1/history")
public class HistoryController {

    @Autowired
    private HistoryServices historyServices;

    @GetMapping("/")
    public ResponseEntity<Object> findAllHistory() {
        var listTasks = historyServices.findAllHistory();
        return new ResponseEntity<>(listTasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdHistory(@PathVariable int id) {
        var categorie = historyServices.findByIdHistory(id);
        return new ResponseEntity<>(categorie, HttpStatus.OK);
    }

    // ======================
    // = Create History =
    // ======================
    @PostMapping("/crear")
    public ResponseEntity<History> createHistory(@RequestBody RequestRegisterHistory request) {
        History createdHistory = historyServices.createHistory(request);
        return ResponseEntity.ok(createdHistory);
    }

    // ======================
    // = Update History =
    // ======================
    @PutMapping("/{id}")
    public ResponseEntity<String> updateHistory(@PathVariable int id, @RequestBody History historyUpdate) {
        try {
            historyServices.update(id, historyUpdate);
            return ResponseEntity.ok("History updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update history: " + e.getMessage());
        }
    }

}
