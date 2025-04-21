package com.sena.TaskManagement.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.TaskManagement.DTOs.RequestRegisterTasksTags;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Interfaces.ITasksTags;
import com.sena.TaskManagement.model.Tags;
import com.sena.TaskManagement.model.Tasks;
import com.sena.TaskManagement.model.TasksTags;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TasksTagsServices {

    @Autowired
    private ITasksTags tasksTagsData;

    public List<RequestRegisterTasksTags> findAllTaskTags() {
        try {
            var TasksTags = tasksTagsData.findAll();
            return MapToList(TasksTags);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos TasksTags" + e);
        }
    }

    public RequestRegisterTasksTags findByIdTaskTags(int id) {
        try {
            // Buscar por su ID
            var TasksTags = tasksTagsData.findById(id);

            // Si no se encuentraa, lanzamos una excepción
            if (!TasksTags.isPresent()) {
                throw new EntityNotFoundException("TasksTags not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(TasksTags.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching TasksTags with ID: " + id, e);
        }
    }

    // Guardar categoria
    public responseDto save(RequestRegisterTasksTags TasksTagsDto) {

        // validar que el id no exista
        if (tasksTagsData.findById(TasksTagsDto.getId()).isPresent()) {
            return new responseDto(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            tasksTagsData.save(MapToEntity(TasksTagsDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto update(RequestRegisterTasksTags TasksTagsDto) {
        try {
            if (TasksTagsDto.getId() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = tasksTagsData.findById(TasksTagsDto.getId());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedTasksTags = MapToEntity(TasksTagsDto);
            tasksTagsData.save(updatedTasksTags);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    public responseDto delete(int id) {
        try {
            var tasksTags = tasksTagsData.findById(id);
            if (tasksTags.isPresent()) {
                tasksTagsData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo
    public TasksTags MapToEntity(RequestRegisterTasksTags tasksTagsDto) {

        Tasks tasks = new Tasks();
        tasks.setId(tasksTagsDto.getTaskId());

        Tags tags = new Tags();
        tags.setId(tasksTagsDto.getTagsId());

        return new TasksTags(
                tasksTagsDto.getId(),
                tasks,
                tags);

    }

    // Mapeo de Entidad a Dto
    public RequestRegisterTasksTags MapToDto(TasksTags entity) {
        return new RequestRegisterTasksTags(
                entity.getId(),
                entity.getTags().getId(),
                entity.getTasks().getId());
    }

    // Mapeao de entidad a lista de Dto
    public List<RequestRegisterTasksTags> MapToList(List<TasksTags> entities) {
        List<RequestRegisterTasksTags> dtos = new ArrayList<>();
        for (TasksTags entity : entities) {
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
