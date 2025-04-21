package com.sena.TaskManagement.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterTaskStatus;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Interfaces.ITaskStatus;
import com.sena.TaskManagement.model.TaskStatus;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskStatusServices {

    @Autowired
    private ITaskStatus taskStatusData;

    // Obteber todos los categorias
    public List<RequestRegisterTaskStatus> findAll() {
        try {
            var TaskStatus = taskStatusData.findAll();
            return MapToList(TaskStatus);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos TaskStatus" + e);
        }
    }

    // // Obtener por nombre
    // public List<TaskStatus> findByNameauthor(String name) {
    // return taskStatusData.findByName(name);
    // }

    // Obteber un categoria por id
    public RequestRegisterTaskStatus findById(int id) {
        TaskStatus category = taskStatusData.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + id));

        return MapToDto(category);
    }

    // Guardar categoria
    public responseDto save(RequestRegisterTaskStatus taskStatusDto) {
        // validar que el id no exista
        if (taskStatusData.findById(taskStatusDto.getId()).isPresent()) {
            return new responseDto(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            // Convertir DTO a Entidad
            TaskStatus categoryEntity = MapToEntity(taskStatusDto);
            taskStatusData.save(categoryEntity); // Guardar la entidad
            return createResponse(HttpStatus.CREATED, "Categoría creada correctamente");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear: " + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto update(RequestRegisterTaskStatus TaskStatusDto) {
        try {
            if (TaskStatusDto.getId() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = taskStatusData.findById(TaskStatusDto.getId());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedauthor = MapToEntity(TaskStatusDto);
            taskStatusData.save(updatedauthor);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    public responseDto delete(int id) {
        try {
            var TaskStatus = taskStatusData.findById(id);
            if (TaskStatus.isPresent()) {
                taskStatusData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo categoria
    public TaskStatus MapToEntity(RequestRegisterTaskStatus TaskStatusDto) {
        return new TaskStatus(
                TaskStatusDto.getId(),
                TaskStatusDto.getName(),
                null);

    }

    // Mapeo de Entidad a Dto
    public RequestRegisterTaskStatus MapToDto(TaskStatus entity) {
        return new RequestRegisterTaskStatus(
                entity.getId(),
                entity.getName());
    }

    // Mapeao de entidad a lista de Dto
    public List<RequestRegisterTaskStatus> MapToList(List<TaskStatus> entities) {
        List<RequestRegisterTaskStatus> dtos = new ArrayList<>();
        for (TaskStatus entity : entities) {
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
