package com.sena.TaskManagement.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterTags;
import com.sena.TaskManagement.Interfaces.ITags;
import com.sena.TaskManagement.model.Tags;

@Service
public class TagsServices {

    /*
     * @Autowired = Incluye la conexión de la interface
     */

     @Autowired
     private ITags tagsData;

    
    public List<Tags> findAllTags() {
        return tagsData.findAll();
    }
    
    public Optional<Tags> findByIdTags(int id) {
        return tagsData.findById(id);
    }

    public void save(RequestRegisterTags Tags) {
        tagsData.save(convertRegisterToTags(Tags));
    }

    public Tags convertRegisterToTags(RequestRegisterTags Tags) {
        // Se pasa solo el nombre y una lista vacía de tasksCategories
        return new Tags(
            0,
            Tags.getName(),
            new ArrayList<>() // Taks

        );
    }
    
    public Tags createTags(RequestRegisterTags request) {
        Tags Tags = new Tags();
        Tags.setName(request.getName());
        return tagsData.save(Tags);
    }

    public void update(int id, Tags tagsUpdate) {
        var Tags = findByIdTags(id);
        if (Tags.isPresent()) {
            Tags.get().setName(tagsUpdate.getName());
            tagsData.save(Tags.get());

        }
    }

    public void delete(int id) {
        var Tags = findByIdTags(id);
        if (Tags.isPresent()) {// trae un boolean si existe o no
            tagsData.delete(Tags.get());
        }
    }


    // public List<Tags> filterForTitle(String title) {
    //     return tagsData.findByTitleContainingIgnoreCase(title);
    // }
}