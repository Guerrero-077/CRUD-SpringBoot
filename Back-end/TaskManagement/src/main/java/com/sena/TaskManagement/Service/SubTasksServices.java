package com.sena.TaskManagement.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterSubTasks;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Interfaces.ISubTasks;
import com.sena.TaskManagement.model.SubTasks;
import com.sena.TaskManagement.model.Tasks;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SubTasksServices {

    /*
     * @Autowired = Incluye la conexión de la interface
     */

    @Autowired
    private ISubTasks subTasksData;

    // Obteber todos los categorias
    public List<RequestRegisterSubTasks> findAll() {
        try {
            var SubTasks = subTasksData.findAll();
            return MapToList(SubTasks);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos SubTasks" + e);
        }
    }

    // // Obtener por nombre
    // public List<SubTasks> findByNameauthor(String name) {
    // return subTasksData.findByName(name);
    // }

    // Obteber un SubTasks por id
    public RequestRegisterSubTasks findById(int id) {
        try {
            // Buscar la categoría por su ID
            var SubTasks = subTasksData.findById(id);

            if (!SubTasks.isPresent()) {
                throw new EntityNotFoundException("SubTasks not found with ID: " + id);
            }
            return MapToDto(SubTasks.get());

        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching SubTasks with ID: " + id, e);
        }
    }

    // Guardar SubTasks
    public responseDto save(RequestRegisterSubTasks SubTasksDto) {
        // validar que el id no exista
        if (subTasksData.findById(SubTasksDto.getId()).isPresent()) {
            return new responseDto(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            subTasksData.save(MapToEntity(SubTasksDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar SubTasks
    public responseDto update(RequestRegisterSubTasks SubTasksDto) {
        try {
            if (SubTasksDto.getId() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = subTasksData.findById(SubTasksDto.getId());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedauthor = MapToEntity(SubTasksDto);
            subTasksData.save(updatedauthor);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    public responseDto delete(int id) {
        try {
            var SubTasks = subTasksData.findById(id);
            if (SubTasks.isPresent()) {
                subTasksData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo SubTasks
    public SubTasks MapToEntity(RequestRegisterSubTasks SubTasksDto) {
        Tasks task = new Tasks();
        task.setId(SubTasksDto.getTask_id());

        return new SubTasks(
                SubTasksDto.getId(),
                SubTasksDto.getName(),
                task);

    }

    // Mapeo de Entidad a Dto
    public RequestRegisterSubTasks MapToDto(SubTasks entity) {
        return new RequestRegisterSubTasks(
                entity.getId(),
                entity.getName(),
                entity.getTasksForSubTasks().getId());
    }

    // Mapeao de entidad a lista de Dto
    public List<RequestRegisterSubTasks> MapToList(List<SubTasks> entities) {
        List<RequestRegisterSubTasks> dtos = new ArrayList<>();
        for (SubTasks entity : entities) {
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
