package com.sena.TaskManagement.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterTasksCategories;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Interfaces.ITasksCategories;
import com.sena.TaskManagement.model.Categories;
import com.sena.TaskManagement.model.Tasks;
import com.sena.TaskManagement.model.TasksCategories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskCategoriesService {

    @Autowired
    private ITasksCategories tasksCategoriesData;

    public List<RequestRegisterTasksCategories> findAll() {
        try {
            var TasksCategories = tasksCategoriesData.findAll();
            return MapToList(TasksCategories);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos TasksCategories" + e);
        }
    }

    public RequestRegisterTasksCategories findById(int id) {
        try {
            // Buscar por su ID
            var TasksCategories = tasksCategoriesData.findById(id);

            // Si no se encuentraa, lanzamos una excepción
            if (!TasksCategories.isPresent()) {
                throw new EntityNotFoundException("TasksCategories not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(TasksCategories.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching TasksCategories with ID: " + id, e);
        }
    }

    // Guardar categoria
    public responseDto save(RequestRegisterTasksCategories TasksTagsDto) {

        // validar que el id no exista
        if (tasksCategoriesData.findById(TasksTagsDto.getId()).isPresent()) {
            return new responseDto(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            tasksCategoriesData.save(MapToEntity(TasksTagsDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto update(RequestRegisterTasksCategories TasksTagsDto) {
        try {
            if (TasksTagsDto.getId() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = tasksCategoriesData.findById(TasksTagsDto.getId());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedTasksTags = MapToEntity(TasksTagsDto);
            tasksCategoriesData.save(updatedTasksTags);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    public responseDto delete(int id) {
        try {
            var TasksCategories = tasksCategoriesData.findById(id);
            if (TasksCategories.isPresent()) {
                tasksCategoriesData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo
    public TasksCategories MapToEntity(RequestRegisterTasksCategories tasksTagsDto) {

        Tasks tasks = new Tasks();
        tasks.setId(tasksTagsDto.getTaskId());

        Categories categories = new Categories();
        categories.setId(tasksTagsDto.getCategoriesId());

        return new TasksCategories(
                tasksTagsDto.getId(),
                tasks,
                categories);

    }

    // Mapeo de Entidad a Dto
    public RequestRegisterTasksCategories MapToDto(TasksCategories entity) {
        return new RequestRegisterTasksCategories(
                entity.getId(),
                entity.getCategories().getId(),
                entity.getTasks().getId());
    }

    // Mapeao de entidad a lista de Dto
    public List<RequestRegisterTasksCategories> MapToList(List<TasksCategories> entities) {
        List<RequestRegisterTasksCategories> dtos = new ArrayList<>();
        for (TasksCategories entity : entities) {
            dtos.add(MapToDto(entity));
        }
        return dtos;
    }

    // Response
    public responseDto createResponse(HttpStatus status, String message) {
        responseDto response = new responseDto();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }
}