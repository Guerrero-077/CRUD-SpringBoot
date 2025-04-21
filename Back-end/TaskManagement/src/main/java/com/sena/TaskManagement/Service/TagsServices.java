package com.sena.TaskManagement.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterTags;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Interfaces.ITags;
import com.sena.TaskManagement.model.Tags;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TagsServices {

    /*
     * @Autowired = Incluye la conexión de la interface
     */

    @Autowired
    private ITags tagsData;

    // Obteber todos los categorias
    public List<RequestRegisterTags> findAll() {
        try {
            var Tags = tagsData.findAll();
            return MapToList(Tags);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos Tags" + e);
        }
    }

    // // Obtener por nombre
    // public List<Tags> findByNameauthor(String name) {
    // return tagsData.findByName(name);
    // }

    // Obteber un categoria por id
    public RequestRegisterTags findById(int id) {
        try {
            // Buscar la categoría por su ID
            var Tags = tagsData.findById(id);

            // Si no se encuentra la categoría, lanzamos una excepción
            if (!Tags.isPresent()) {
                throw new EntityNotFoundException("Tags not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(Tags.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra la categoría
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching Tags with ID: " + id, e);
        }
    }

    // Guardar categoria
    public responseDto save(RequestRegisterTags TagsDto) {

        // validar que el id no exista
        if (tagsData.findById(TagsDto.getId()).isPresent()) {
            return new responseDto(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            tagsData.save(MapToEntity(TagsDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto update(RequestRegisterTags TagsDto) {
        try {
            if (TagsDto.getId() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = tagsData.findById(TagsDto.getId());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedauthor = MapToEntity(TagsDto);
            tagsData.save(updatedauthor);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    public responseDto delete(int id) {
        try {
            var Tags = tagsData.findById(id);
            if (Tags.isPresent()) {
                tagsData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo categoria
    public Tags MapToEntity(RequestRegisterTags TagsDto) {
        return new Tags(
                TagsDto.getId(),
                TagsDto.getName(),
                null);

    }

    // Mapeo de Entidad a Dto
    public RequestRegisterTags MapToDto(Tags entity) {
        return new RequestRegisterTags(
                entity.getId(),
                entity.getName());
    }

    // Mapeao de entidad a lista de Dto
    public List<RequestRegisterTags> MapToList(List<Tags> entities) {
        List<RequestRegisterTags> dtos = new ArrayList<>();
        for (Tags entity : entities) {
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