package com.sena.TaskManagement.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterReminders;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Interfaces.IReminders;
import com.sena.TaskManagement.model.Reminders;
import com.sena.TaskManagement.model.Tasks;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RemindersServices {
    /*
     * @Autowired = Incluye la conexión de la interface
     */

    @Autowired
    private IReminders remindersData;

    // Obteber todos los categorias
    public List<RequestRegisterReminders> findAll() {
        try {
            var reminders = remindersData.findAll();
            return MapToList(reminders);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos reminders" + e);
        }
    }

    // // Obtener por nombre
    // public List<reminders> findByNameauthor(String name) {
    // return remindersData.findByName(name);
    // }

    // Obteber un Reminders por id
    public RequestRegisterReminders findById(int id) {
        try {
            // Buscar la categoría por su ID
            var reminders = remindersData.findById(id);
            if (!reminders.isPresent()) {
                throw new EntityNotFoundException("reminders not found with ID: " + id);
            }
            return MapToDto(reminders.get());

        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching reminders with ID: " + id, e);
        }
    }

    // Guardar Reminders
    public responseDto save(RequestRegisterReminders remindersDto) {
        // validar que el id no exista
        if (remindersData.findById(remindersDto.getId()).isPresent()) {
            return new responseDto(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            remindersData.save(MapToEntity(remindersDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar Reminders
    public responseDto update(RequestRegisterReminders remindersDto) {
        try {
            if (remindersDto.getId() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = remindersData.findById(remindersDto.getId());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedauthor = MapToEntity(remindersDto);
            remindersData.save(updatedauthor);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    public responseDto delete(int id) {
        try {
            var reminders = remindersData.findById(id);
            if (reminders.isPresent()) {
                remindersData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo Reminders
    public Reminders MapToEntity(RequestRegisterReminders remindersDto) {

        Tasks tasks = new Tasks();
        tasks.setId(remindersDto.getTask_id());
        return new Reminders(
                remindersDto.getId(),
                remindersDto.getName(),
                tasks);

    }

    // Mapeo de Entidad a Dto
    public RequestRegisterReminders MapToDto(Reminders entity) {
        return new RequestRegisterReminders(
                entity.getId(),
                entity.getName(),
                entity.getTasksForReminders().getId());
    }

    // Mapeao de entidad a lista de Dto
    public List<RequestRegisterReminders> MapToList(List<Reminders> entities) {
        List<RequestRegisterReminders> dtos = new ArrayList<>();
        for (Reminders entity : entities) {
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
