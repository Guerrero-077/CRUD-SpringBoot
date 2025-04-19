package com.sena.TaskManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterTasksTags;
import com.sena.TaskManagement.Interfaces.ITags;
import com.sena.TaskManagement.Interfaces.ITasks;
import com.sena.TaskManagement.Interfaces.ITasksTags;
import com.sena.TaskManagement.model.Tags;
import com.sena.TaskManagement.model.Tasks;
import com.sena.TaskManagement.model.TasksTags;

@Service
public class TasksTagsServices {

    @Autowired
    private ITasksTags tasksTagsData;

    @Autowired
    private ITasks tasksData;

    @Autowired
    private ITags tagsData;

    public List<TasksTags> findAllTaskTags() {
        return tasksTagsData.findAll();
    }

    public Optional<TasksTags> findByIdTaskTags(int id) {
        return tasksTagsData.findById(id);
    }

    public void save(RequestRegisterTasksTags request) {
        TasksTags newRelation = convertRegisterToTasksTags(request);
        if (newRelation != null) {
            tasksTagsData.save(newRelation);
        }
    }

    private TasksTags convertRegisterToTasksTags(RequestRegisterTasksTags dto) {
        Optional<Tasks> task = tasksData.findById(dto.getTaskId());
        Optional<Tags> tags = tagsData.findById(dto.getTagsId());

        if (task.isPresent() && tags.isPresent()) {
            return new TasksTags(0, task.get(), tags.get());
        }

        return null; // o lanza una excepción si quieres manejar errores
    }

    public TasksTags create(RequestRegisterTasksTags request) {
        Optional<Tasks> task = tasksData.findById(request.getTaskId());
        Optional<Tags> tags = tagsData.findById(request.getTagsId());

        if (task.isPresent() && tags.isPresent()) {
            TasksTags tasksTags = new TasksTags();
            tasksTags.setTasks(task.get());
            tasksTags.setTags(tags.get());

            return tasksTagsData.save(tasksTags);
        }

        return null; // o lanzar una excepción si prefieres manejo explícito
    }

    public void update(int id, RequestRegisterTasksTags request) {
        Optional<TasksTags> existing = tasksTagsData.findById(id);

        if (existing.isPresent()) {
            Optional<Tasks> task = tasksData.findById(request.getTaskId());
            Optional<Tags> category = tagsData.findById(request.getTagsId());

            if (task.isPresent() && category.isPresent()) {
                TasksTags toUpdate = existing.get();
                toUpdate.setTasks(task.get());
                toUpdate.setTags(category.get());

                tasksTagsData.save(toUpdate);
            }
        }
    }

    public void delete(int id) {
        Optional<TasksTags> existing = tasksTagsData.findById(id);
        existing.ifPresent(tasksTagsData::delete);
    }

}
