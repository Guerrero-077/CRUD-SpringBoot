package com.sena.TaskManagement.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterCategories;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Interfaces.ICategories;
import com.sena.TaskManagement.model.Categories;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriesServices {

    @Autowired
    private ICategories categoriesData;

    // Obteber todos los categorias
    public List<RequestRegisterCategories> findAllCategory() {
        try {
            var authors = categoriesData.findAll();
            return MapToList(authors);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos Categories" + e);
        }
    }

    // // Obtener por nombre
    // public List<Categories> findByNameauthor(String name) {
    // return categoriesData.findByName(name);
    // }

    // Obteber un categoria por id
    public RequestRegisterCategories findByIdCategories(int id) {
        Categories category = categoriesData.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + id));

        return MapToDto(category);
    }

    // Guardar categoria
    public responseDto save(RequestRegisterCategories categoryDto) {

        // validar que el id no exista
        if (categoriesData.findById(categoryDto.getId()).isPresent()) {
            return new responseDto(HttpStatus.BAD_REQUEST, "El id ya existe");
        }

        try {
            // Convertir DTO a Entidad
            Categories categoryEntity = MapToEntity(categoryDto);
            categoriesData.save(categoryEntity); // Guardar la entidad
            return createResponse(HttpStatus.CREATED, "Categoría creada correctamente");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear: " + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto update(int id, RequestRegisterCategories categoriesDto) {
        try {
            if (id <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = categoriesData.findById(id);
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            categoriesDto.setId(id);
            var updatedauthor = MapToEntity(categoriesDto);
            categoriesData.save(updatedauthor);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    public responseDto delete(int id) {
        try {
            var Categories = categoriesData.findById(id);
            if (Categories.isPresent()) {
                categoriesData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo categoria
    public Categories MapToEntity(RequestRegisterCategories categoriesDto) {
        return new Categories(
                categoriesDto.getId(),
                categoriesDto.getName(),
                null);

    }

    // Mapeo de Entidad a Dto
    public RequestRegisterCategories MapToDto(Categories entity) {
        return new RequestRegisterCategories(
                entity.getId(),
                entity.getName());
    }

    // Mapeao de entidad a lista de Dto
    public List<RequestRegisterCategories> MapToList(List<Categories> entities) {
        List<RequestRegisterCategories> dtos = new ArrayList<>();
        for (Categories entity : entities) {
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
