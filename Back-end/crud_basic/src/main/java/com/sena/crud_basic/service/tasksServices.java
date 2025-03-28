package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.interfaces.ITareas;
import com.sena.crud_basic.model.tasks;

@Service
public class tasksServices {
    /*
     * @Autowired = Incluye la conexión de la interface
     */
    @Autowired
    private ITareas TareasData;

    public List<tasks> findAllTareas() {
        return TareasData.findAll();
    }

    public Optional<tasks> findByIdTareas(int id) {
        return TareasData.findById(id);
    }

    public void save(tasks task) {
        TareasData.save(task);
    }

    public void update(int id, tasks taskUpdate) {
        var task = findByIdTareas(id);
        if (task.isPresent()) {
            task.get().setTitle(taskUpdate.getTitle());
            task.get().setDescription(taskUpdate.getDescription());
            TareasData.save(task.get());

        }
    }

    public void delete(int id) {
        var task = findByIdTareas(id);
        if (task.isPresent()) {// trae un boolean si existe o no
            TareasData.delete(task.get());
        }
    }
}
