package com.sena.TaskManagement.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterPriorities;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Interfaces.IPriorities;
import com.sena.TaskManagement.model.Priorities;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PrioritiesServices {
    /*
     * @Autowired = Incluye la conexión de la interface
     */

    @Autowired
    private IPriorities prioritiesData;

    // Obteber todos los categorias
    public List<RequestRegisterPriorities> findAll() {
        try {
            var authors = prioritiesData.findAll();
            return MapToList(authors);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos Priorities" + e);
        }
    }

    // // Obtener por nombre
    // public List<Priorities> findByNameauthor(String name) {
    // return prioritiesData.findByName(name);
    // }

    // Obteber un categoria por id
    public RequestRegisterPriorities findById(int id) {
        try {
            // Buscar la categoría por su ID
            var Priorities = prioritiesData.findById(id);

            // Si no se encuentra la categoría, lanzamos una excepción
            if (!Priorities.isPresent()) {
                throw new EntityNotFoundException("Priorities not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(Priorities.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra la categoría
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching Priorities with ID: " + id, e);
        }
    }

    // Guardar categoria
    public responseDto save(RequestRegisterPriorities prioritiesDto) {
        // validar que el id no exista
        if (prioritiesData.findById(prioritiesDto.getId()).isPresent()) {
            return new responseDto(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            prioritiesData.save(MapToEntity(prioritiesDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto update(RequestRegisterPriorities prioritiesDto) {
        try {
            if (prioritiesDto.getId() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = prioritiesData.findById(prioritiesDto.getId());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedauthor = MapToEntity(prioritiesDto);
            prioritiesData.save(updatedauthor);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    public responseDto delete(int id) {
        try {
            var Priorities = prioritiesData.findById(id);
            if (Priorities.isPresent()) {
                prioritiesData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo categoria
    public Priorities MapToEntity(RequestRegisterPriorities prioritiesDto) {
        return new Priorities(
                prioritiesDto.getId(),
                prioritiesDto.getName());

    }

    // Mapeo de Entidad a Dto
    public RequestRegisterPriorities MapToDto(Priorities entity) {
        return new RequestRegisterPriorities(
                entity.getId(),
                entity.getName());
    }

    // Mapeao de entidad a lista de Dto
    public List<RequestRegisterPriorities> MapToList(List<Priorities> entities) {
        List<RequestRegisterPriorities> dtos = new ArrayList<>();
        for (Priorities entity : entities) {
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
