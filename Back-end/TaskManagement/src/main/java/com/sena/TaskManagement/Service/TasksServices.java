package com.sena.TaskManagement.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterTask;
import com.sena.TaskManagement.Interfaces.ITasks;
import com.sena.TaskManagement.model.Tasks;

@Service
public class TasksServices {
    /*
     * @Autowired = Incluye la conexión de la interface
     */
    @Autowired
    private ITasks tasksData;

    // Obtener todas las tareas
    public List<Tasks> findAllTasks() {
        return tasksData.findAll();
    }

    // Obtener tarea por ID
    public Optional<Tasks> findTaskById(int id) {
        return tasksData.findById(id);
    }

    // Crear tarea desde un DTO
    public Tasks createTask(RequestRegisterTask request) {
        Tasks task = new Tasks();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCreation_date(LocalDate.now());
        task.setExpiration_date(request.getExpirationDate());
        task.setActive(true);
        return tasksData.save(task);
    }

    // Actualizar tarea
    public Optional<Tasks> updateTask(int id, Tasks taskUpdate) {
        Optional<Tasks> task = findTaskById(id);
        if (task.isPresent()) {
            task.get().setTitle(taskUpdate.getTitle());
            task.get().setDescription(taskUpdate.getDescription());
            return Optional.of(tasksData.save(task.get()));
        } else {
            return Optional.empty(); // Retorna un Optional vacío si no existe la tarea
        }
    }

    // Eliminar tarea
    public boolean deleteTask(int id) {
        Optional<Tasks> task = findTaskById(id);
        if (task.isPresent()) {
            tasksData.delete(task.get());
            return true;
        }
        return false; // Si no existe la tarea
    }

    // Filtrar tareas por título
    public List<Tasks> searchTasksByTitle(String title) {
        return tasksData.findByTitleContainingIgnoreCase(title);
    }
}
