package com.sena.TaskManagement.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.HitoryWhitDetail;
import com.sena.TaskManagement.DTOs.RequestRegisterHistory;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Interfaces.IHistory;
import com.sena.TaskManagement.model.History;
import com.sena.TaskManagement.model.Tasks;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HistoryServices {

    /*
     * @Autowired = Incluye la conexión de la interface
     */

    @Autowired
    private IHistory historyData;

public List<HitoryWhitDetail> getHistHitoryWhitDetails(){
    return historyData.findHitoryWhitDetail();
}
    
    // Obteber todos los categorias
    public List<RequestRegisterHistory> findAll() {
        try {
            var history = historyData.findAll();
            return MapToList(history);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos History" + e);
        }
    }

    // // Obtener por nombre
    // public List<History> findByNameauthor(String name) {
    // return historyData.findByName(name);
    // }

    // Obteber un categoria por id
    public RequestRegisterHistory findById(int id) {
        try {
            // Buscar la categoría por su ID
            var History = historyData.findById(id);

            // Si no se encuentra la categoría, lanzamos una excepción
            if (!History.isPresent()) {
                throw new EntityNotFoundException("History not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(History.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra la categoría
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching History with ID: " + id, e);
        }
    }

    // Guardar categoria
    public responseDto save(RequestRegisterHistory categoriesDto) {
        // validar que el id no exista
        if (historyData.findById(categoriesDto.getId()).isPresent()) {
            return new responseDto(HttpStatus.BAD_REQUEST, "El id ya existe");
        }

        try {
            historyData.save(MapToEntity(categoriesDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto update(RequestRegisterHistory categoriesDto) {
        try {
            if (categoriesDto.getId() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = historyData.findById(categoriesDto.getId());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedauthor = MapToEntity(categoriesDto);
            historyData.save(updatedauthor);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    public responseDto delete(int id) {
        try {
            var History = historyData.findById(id);
            if (History.isPresent()) {
                historyData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo categoria
    public History MapToEntity(RequestRegisterHistory categoriesDto) {

        Tasks taskForHistory = new Tasks();
        taskForHistory.setId(categoriesDto.getTask_id());

        return new History(
                categoriesDto.getId(),
                categoriesDto.getHistory_action(),
                LocalDate.now(),
                taskForHistory);

    }

    // Mapeo de Entidad a Dto
    public RequestRegisterHistory MapToDto(History entity) {
        return new RequestRegisterHistory(
            entity.getId(),
            entity.getHistory_action(),
            entity.getHistory_date(),
            entity.getTaskForHistory().getId()
                );
    }

    // Mapeao de entidad a lista de Dto
    public List<RequestRegisterHistory> MapToList(List<History> entities) {
        List<RequestRegisterHistory> dtos = new ArrayList<>();
        for (History entity : entities) {
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
