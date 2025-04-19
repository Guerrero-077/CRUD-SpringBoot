    package com.sena.TaskManagement.Service;

    import java.util.List;
    import java.util.Optional;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import com.sena.TaskManagement.DTOs.RequestRegisterTasksCategories;
    import com.sena.TaskManagement.Interfaces.ICategories;
    import com.sena.TaskManagement.Interfaces.ITasks;
    import com.sena.TaskManagement.Interfaces.ITasksCategories;
    import com.sena.TaskManagement.model.Categories;
    import com.sena.TaskManagement.model.Tasks;
    import com.sena.TaskManagement.model.TasksCategories;

    @Service
    public class TaskCategoriesServices {

        @Autowired
        private ITasksCategories tasksCategoriesData;

        @Autowired
        private ITasks tasksData;

        @Autowired
        private ICategories categoriesData;

        public List<TasksCategories> findAllTaskCategories() {
            return tasksCategoriesData.findAll();
        }

        public Optional<TasksCategories> findByIdTaskCategories(int id) {
            return tasksCategoriesData.findById(id);
        }

        public void save(RequestRegisterTasksCategories request) {
            TasksCategories newRelation = convertRegisterToTasksCategories(request);
            if (newRelation != null) {
                tasksCategoriesData.save(newRelation);
            }
        }

        private TasksCategories convertRegisterToTasksCategories(RequestRegisterTasksCategories dto) {
            Optional<Tasks> task = tasksData.findById(dto.getTaskId());
            Optional<Categories> category = categoriesData.findById(dto.getCategoriesId());

            if (task.isPresent() && category.isPresent()) {
                return new TasksCategories(0, task.get(), category.get());
            }

            return null; // o lanza una excepción si quieres manejar errores
        }

        public TasksCategories create(RequestRegisterTasksCategories request) {
            Optional<Tasks> task = tasksData.findById(request.getTaskId());
            Optional<Categories> category = categoriesData.findById(request.getCategoriesId());

            if (task.isPresent() && category.isPresent()) {
                TasksCategories taskCategory = new TasksCategories();
                taskCategory.setTasks(task.get());
                taskCategory.setCategories(category.get());

                return tasksCategoriesData.save(taskCategory);
            }

            return null; // o lanzar una excepción si prefieres manejo explícito
        }

        public void update(int id, RequestRegisterTasksCategories request) {
            Optional<TasksCategories> existing = tasksCategoriesData.findById(id);

            if (existing.isPresent()) {
                Optional<Tasks> task = tasksData.findById(request.getTaskId());
                Optional<Categories> category = categoriesData.findById(request.getCategoriesId());

                if (task.isPresent() && category.isPresent()) {
                    TasksCategories toUpdate = existing.get();
                    toUpdate.setTasks(task.get());
                    toUpdate.setCategories(category.get());

                    tasksCategoriesData.save(toUpdate);
                }
            }
        }

        public void delete(int id) {
            Optional<TasksCategories> existing = tasksCategoriesData.findById(id);
            existing.ifPresent(tasksCategoriesData::delete);
        }

    }
