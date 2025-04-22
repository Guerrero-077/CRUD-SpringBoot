package com.sena.TaskManagement.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.Registrar;
import com.sena.TaskManagement.DTOs.RequestRegisterReminders;
import com.sena.TaskManagement.DTOs.RequestRegisterSubTasks;
import com.sena.TaskManagement.DTOs.RequestRegisterTask;
import com.sena.TaskManagement.DTOs.TaskWithDetails;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Interfaces.ICategories;
import com.sena.TaskManagement.Interfaces.IHistory;
import com.sena.TaskManagement.Interfaces.IPriorities;
import com.sena.TaskManagement.Interfaces.IReminders;
import com.sena.TaskManagement.Interfaces.ISubTasks;
import com.sena.TaskManagement.Interfaces.ITags;
import com.sena.TaskManagement.Interfaces.ITaskStatus;
import com.sena.TaskManagement.Interfaces.ITasks;
import com.sena.TaskManagement.Interfaces.ITasksCategories;
import com.sena.TaskManagement.Interfaces.ITasksTags;
import com.sena.TaskManagement.model.History;
import com.sena.TaskManagement.model.Priorities;
import com.sena.TaskManagement.model.Reminders;
import com.sena.TaskManagement.model.SubTasks;
import com.sena.TaskManagement.model.TaskStatus;
import com.sena.TaskManagement.model.Tasks;
import com.sena.TaskManagement.model.TasksCategories;
import com.sena.TaskManagement.model.TasksTags;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TasksServices {

    @Autowired
    private ITasks tasksData;

    @Autowired
    private IPriorities prioritiesRepository;

    @Autowired
    private ITaskStatus taskStatusRepository;

    @Autowired
    private ICategories categoriesRepository;

    @Autowired
    private ITasksCategories tasksCategoriesRepository;

    @Autowired
    private ITags tagsRepository;

    @Autowired
    private ISubTasks tasksSubTasksRepository;

    @Autowired
    private ITasksTags tasksTagsRepository;

    @Autowired
    private IReminders remindersRepository;

    @Autowired
    private IHistory historyRepository;

    public List<TaskWithDetails> getTasksWithDetails() {
        return tasksData.findAllWithDetails();
    }

    // Obteber todo
    public List<RequestRegisterTask> findAll() {
        try {
            var Tasks = tasksData.findAll();
            return MapToList(Tasks);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos Tasks" + e);
        }
    }


    //Obterner por prioeidad
    public List<TaskWithDetails> getFilter(int id){
        return tasksData.filterPriority(id);
    }

    // Obteber por id
    public RequestRegisterTask findById(int id) {
        try {
            // Buscar por su ID
            var Tasks = tasksData.findById(id);

            // Si no se encuentraa, lanzamos una excepción
            if (!Tasks.isPresent()) {
                throw new EntityNotFoundException("Tasks not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(Tasks.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching Tasks with ID: " + id, e);
        }
    }

    public responseDto save(Registrar TasksDto) {
        try {
            if (TasksDto.getExpirationDate() == null || TasksDto.getExpirationDate().isBefore(LocalDate.now())) {
                return createResponse(HttpStatus.BAD_REQUEST,
                        "La fecha de expiración debe ser hoy o una fecha futura.");
            }
            Tasks task = MapToEntity(TasksDto);
            tasksData.save(task);
            saveRelations(task, TasksDto);
            return createResponse(HttpStatus.CREATED, "Se creó correctamente");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear: " + e.getMessage());
        }
    }

    public void saveRelations(Tasks task, Registrar dto) {
        // Guardar categorías
        List<TasksCategories> relacionesCategorias = new ArrayList<>();
        for (Integer categoryId : dto.getCategoryIds()) {
            var category = categoriesRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + categoryId));
            relacionesCategorias.add(new TasksCategories(0, task, category));
        }
        tasksCategoriesRepository.saveAll(relacionesCategorias);

        // Guardar tags
        List<TasksTags> relacionesTags = new ArrayList<>();
        for (Integer tagId : dto.getTagIds()) {
            var tag = tagsRepository.findById(tagId)
                    .orElseThrow(() -> new RuntimeException("Tag no encontrado con ID: " + tagId));
            relacionesTags.add(new TasksTags(0, task, tag));
        }
        tasksTagsRepository.saveAll(relacionesTags);

        // Guardar subtareas
        List<SubTasks> tareasSubtasks = new ArrayList<>();
        for (RequestRegisterSubTasks subDto : dto.getSubTasks()) {
            SubTasks nuevaSub = new SubTasks();
            nuevaSub.setName(subDto.getName());
            nuevaSub.setTasksForSubTasks(task);
            tareasSubtasks.add(nuevaSub);
        }
        tasksSubTasksRepository.saveAll(tareasSubtasks);

        // Guardar recordatorios (reminders)
        List<Reminders> tareasReminders = new ArrayList<>();
        for (RequestRegisterReminders subDto : dto.getReminders()) {
            Reminders nuevaSub = new Reminders();
            nuevaSub.setName(subDto.getName());
            nuevaSub.setTasksForReminders(task);
            tareasReminders.add(nuevaSub);
        }
        remindersRepository.saveAll(tareasReminders);
    }

    // Actualizar
    public responseDto update(int id, Registrar dto) {
        try {
            if (id <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }
            if (dto.getExpirationDate() == null || dto.getExpirationDate().isBefore(LocalDate.now())) {
                return createResponse(HttpStatus.BAD_REQUEST,
                        "La fecha de expiración debe ser hoy o una fecha futura.");
            }

            // Verificar existencia
            var existing = tasksData.findById(id);
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró la tarea con ese ID");
            }

            // Crear/actualizar la tarea
            RequestRegisterTask taskDto = new RequestRegisterTask(
                    id, dto.getName(), dto.getDescription(), LocalDate.now(), dto.getExpirationDate(),
                    dto.isActive(), dto.getPriorityId(), dto.getTaskStatusId());
            Tasks updatedTask = MapToEntity(taskDto);

            // Guardar la tarea actualizada
            tasksData.save(updatedTask);

            // Guardar las relaciones adicionales
            saveRelations(updatedTask, dto);

            return createResponse(HttpStatus.OK, "Tarea actualizada correctamente");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar la tarea: " + e.getMessage());
        }
    }

    public responseDto toggleActive(int id, boolean newActive) {
        try {
            var optionalTask = tasksData.findById(id);
            if (!optionalTask.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró la tarea con ID: " + id);
            }

            Tasks task = optionalTask.get();
            boolean wasActive = task.isActive();
            task.setActive(newActive);
            tasksData.save(task);

            // Guardar en historial si se desactiva
            if (wasActive && !newActive) {
                History history = new History();
                history.setTaskForHistory(task);
                history.setHistory_date(LocalDate.now());
                history.setHistory_action("La tarea fue marcada como inactiva.");
                historyRepository.save(history);
            }

            return createResponse(HttpStatus.OK, "Estado de tarea actualizado correctamente");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar el estado: " + e.getMessage());
        }
    }

    public responseDto delete(int id) {
        try {
            var Tasks = tasksData.findById(id);
            if (Tasks.isPresent()) {
                tasksData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo
    public Tasks MapToEntity(RequestRegisterTask TasksDto) {

        // Obtener prioridad desde base de datos
        Priorities priorities = prioritiesRepository.findById(TasksDto.getPriorityId())
                .orElseThrow(() -> new RuntimeException("Prioridad no encontrada con ID: " + TasksDto.getPriorityId()));

        // Obtener estado desde base de datos
        TaskStatus taskStatus = taskStatusRepository.findById(TasksDto.getTaskStatusId())
                .orElseThrow(() -> new RuntimeException(
                        "Estado de tarea no encontrado con ID: " + TasksDto.getTaskStatusId()));

        // TaskStatus taskStatus = new TaskStatus();
        // taskStatus.setId(TasksDto.getTaskStatusId());
        return new Tasks(
                TasksDto.getId(),
                TasksDto.getName(),
                TasksDto.getDescription(),
                LocalDate.now(),
                TasksDto.getExpirationDate(),
                TasksDto.isActive(),
                null,
                null,
                null,
                null,
                null,
                taskStatus,
                priorities);

    }

    public Tasks MapToEntity(Registrar dto) {

        Priorities priorities = prioritiesRepository.findById(dto.getPriorityId())
                .orElseThrow(() -> new RuntimeException("Prioridad no encontrada con ID: " + dto.getPriorityId()));

        TaskStatus taskStatus = taskStatusRepository.findById(dto.getTaskStatusId())
                .orElseThrow(
                        () -> new RuntimeException("Estado de tarea no encontrado con ID: " + dto.getTaskStatusId()));

        return new Tasks(
                0, // Asumimos que se está creando, por eso ID en 0 o null
                dto.getName(),
                dto.getDescription(),
                LocalDate.now(),
                dto.getExpirationDate(),
                dto.isActive(),
                null,
                null,
                null,
                null,
                null,
                taskStatus,
                priorities);
    }

    // Mapeo de Entidad a Dto
    public RequestRegisterTask MapToDto(Tasks entity) {
        return new RequestRegisterTask(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCreation_date(),
                entity.getExpiration_date(),
                entity.isActive(),
                entity.getTaskStatus().getId(),
                entity.getPriority().getId());
    }

    // Mapeao de entidad a lista de Dto
    public List<RequestRegisterTask> MapToList(List<Tasks> entities) {
        List<RequestRegisterTask> dtos = new ArrayList<>();
        for (Tasks entity : entities) {
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