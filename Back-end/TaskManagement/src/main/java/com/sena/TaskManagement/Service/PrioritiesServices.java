package com.sena.TaskManagement.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterPriorities;
import com.sena.TaskManagement.Interfaces.IPriorities;
import com.sena.TaskManagement.model.Priorities;

@Service
public class PrioritiesServices {
    /*
     * @Autowired = Incluye la conexión de la interface
     */

    @Autowired
    private IPriorities prioritiesData;

    public List<Priorities> findAllPriorities() {
        return prioritiesData.findAll();
    }

    public Optional<Priorities> findByIdPriorities(int id) {
        return prioritiesData.findById(id);
    }

    public void save(RequestRegisterPriorities Priorities) {
        prioritiesData.save(convertRegisterToPriorities(Priorities));
    }

    public Priorities convertRegisterToPriorities(RequestRegisterPriorities Priorities) {
        // Se pasa solo el nombre y una lista vacía de tasksCategories
        return new Priorities(
                0,
                Priorities.getName()
        );
    }

    public Priorities createPriorities(RequestRegisterPriorities request) {
        Priorities Priorities = new Priorities();
        Priorities.setName(request.getName());
        return prioritiesData.save(Priorities);
    }

    public void update(int id, Priorities prioritiesUpdate) {
        var Priorities = findByIdPriorities(id);
        if (Priorities.isPresent()) {
            Priorities.get().setName(prioritiesUpdate.getName());
            prioritiesData.save(Priorities.get());

        }
    }

    public void delete(int id) {
        var Priorities = findByIdPriorities(id);
        if (Priorities.isPresent()) {// trae un boolean si existe o no
            prioritiesData.delete(Priorities.get());
        }
    }

    // public List<Priorities> filterForTitle(String title) {
    // return prioritiesData.findByTitleContainingIgnoreCase(title);
    // }
}
