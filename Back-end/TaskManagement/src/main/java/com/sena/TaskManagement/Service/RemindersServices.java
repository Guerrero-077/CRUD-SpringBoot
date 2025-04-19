package com.sena.TaskManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterReminders;
import com.sena.TaskManagement.Interfaces.IReminders;
import com.sena.TaskManagement.Interfaces.ITasks;
import com.sena.TaskManagement.model.Reminders;
import com.sena.TaskManagement.model.Tasks;

@Service
public class RemindersServices {
    /*
     * @Autowired = Incluye la conexión de la interface
     */

    @Autowired
    private IReminders remindersData;

    @Autowired
    private ITasks tasksData;

    public List<Reminders> findAllReminders() {
        return remindersData.findAll();
    }

    public Optional<Reminders> findByIdReminders(int id) {
        return remindersData.findById(id);
    }

    public void save(RequestRegisterReminders Reminders) {
        remindersData.save(convertRegisterToReminders(Reminders));
    }

    public Reminders convertRegisterToReminders(RequestRegisterReminders dto) {
        return new Reminders(
                0,
                dto.getTitle(),
                null // o puedes setear un task válido si lo tienes
        );
    }

    public Reminders creaReminders(RequestRegisterReminders request) {
        Tasks task = tasksData.findById(request.getTask_id())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Reminders reminders = new Reminders();
        reminders.setTitle(request.getTitle());
        reminders.setTasksForReminders(task); // ✅ Se asigna correctamente el objeto
        return remindersData.save(reminders);
    }

    public void updateReminders(int id, Reminders request) {
        Optional<Reminders> existingReminders = remindersData.findById(id);
        if (existingReminders.isPresent()) {
            Reminders reminders = existingReminders.get();
            reminders.setTitle(request.getTitle());
            reminders.setTasksForReminders(request.getTasksForReminders()); // Actualiza la relación si es necesario
            remindersData.save(reminders);
        } else {
            throw new RuntimeException("Reminders not found");
        }
    }

    public void deleteReminders(int id) {
        var Reminders = findByIdReminders(id);
        if (Reminders.isPresent()) {// trae un boolean si existe o no
            remindersData.delete(Reminders.get());
        }
    }
}
