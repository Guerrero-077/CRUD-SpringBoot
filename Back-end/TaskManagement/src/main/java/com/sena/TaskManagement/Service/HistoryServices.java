package com.sena.TaskManagement.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterHistory;
import com.sena.TaskManagement.Interfaces.IHistory;
import com.sena.TaskManagement.Interfaces.ITasks;
import com.sena.TaskManagement.model.History;
import com.sena.TaskManagement.model.Tasks;

@Service
public class HistoryServices {

    /*
     * @Autowired = Incluye la conexión de la interface
     */

    @Autowired
    private IHistory historyData;

    @Autowired
    private ITasks tasksData; // Asegúrate de que esto exista

    public List<History> findAllHistory() {
        return historyData.findAll();
    }

    public Optional<History> findByIdHistory(int id) {
        return historyData.findById(id);
    }

    public void save(RequestRegisterHistory History) {
        historyData.save(convertRegisterToHistory(History));
    }

    public History convertRegisterToHistory(RequestRegisterHistory dto) {
        return new History(
                0,
                dto.getHistory_action(),
                LocalDate.now(),
                null // o puedes setear un task válido si lo tienes
        );
    }

    public History createHistory(RequestRegisterHistory request) {
        Tasks task = tasksData.findById(request.getTask_id())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        History history = new History();
        history.setHistory_action(request.getHistory_action());
        history.setHistory_date(LocalDate.now()); // o request.getHistory_date() si deseas usar la fecha del request
        history.setTaskForHistory(task); // ✅ Se asigna correctamente el objeto

        return historyData.save(history);
    }

    public void update(int id, History updatedHistory) {
        Optional<History> optionalHistory = findByIdHistory(id);
        if (optionalHistory.isPresent()) {
            History existingHistory = optionalHistory.get();
            existingHistory.setHistory_action(updatedHistory.getHistory_action());

            historyData.save(existingHistory);
        }
    }

    public void delete(int id) {
        var History = findByIdHistory(id);
        if (History.isPresent()) {// trae un boolean si existe o no
            historyData.delete(History.get());
        }
    }

    // public List<History> filterForTitle(String title) {
    // return historyData.findByTitleContainingIgnoreCase(title);
    // }
}
