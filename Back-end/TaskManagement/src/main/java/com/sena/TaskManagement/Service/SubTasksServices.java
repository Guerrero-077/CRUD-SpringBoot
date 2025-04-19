package com.sena.TaskManagement.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterSubTasks;
import com.sena.TaskManagement.Interfaces.ISubTasks;
import com.sena.TaskManagement.Interfaces.ITasks;
import com.sena.TaskManagement.model.SubTasks;
import com.sena.TaskManagement.model.Tasks;

@Service
public class SubTasksServices {

    /*
     * @Autowired = Incluye la conexión de la interface
     */

    @Autowired
    private ISubTasks subTasksData;
    @Autowired
    private ITasks tasksData;


    
    public List<SubTasks> findAllSubTasks() {
        return subTasksData.findAll();
    }

    public Optional<SubTasks> findByIdSubTasks(int id) {
        return subTasksData.findById(id);
    }

    public void save(RequestRegisterSubTasks SubTasks) {
        subTasksData.save(convertRegisterToSubTasks(SubTasks));
    }

    public SubTasks convertRegisterToSubTasks(RequestRegisterSubTasks requestDto) {
        Tasks task = new Tasks();
        task.setId(requestDto.getTask_id());

        return new SubTasks(
                0, // ID se genera automáticamente
                requestDto.getTitle(),
                task
        );
    }


    public SubTasks createSubTasks(RequestRegisterSubTasks request) {
        Tasks task = tasksData.findById(request.getTask_id())
        .orElseThrow(() -> new RuntimeException("Task not found"));
        SubTasks SubTasks = new SubTasks();
        SubTasks.setTitle(request.getTitle());
        SubTasks.setTasksForSubTasks(task); // Asignar la tarea a la subtarea
        return subTasksData.save(SubTasks);
    }

    public void update(int id, SubTasks prioritiesUpdate) {
        var SubTasks = findByIdSubTasks(id);
        if (SubTasks.isPresent()) {
            SubTasks.get().setTitle(prioritiesUpdate.getTitle());
            subTasksData.save(SubTasks.get());

        }
    }

    public void delete(int id) {
        var SubTasks = findByIdSubTasks(id);
        if (SubTasks.isPresent()) {// trae un boolean si existe o no
            subTasksData.delete(SubTasks.get());
        }
    }
}
