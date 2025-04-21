package com.sena.TaskManagement.DTOs;

import java.time.LocalDate;
import java.util.List;

public interface HitoryWhitDetail {
    Integer getHistoryId();
    String getName();
    String getDescription();
    LocalDate getCreationDate();
    LocalDate getExpirationDate();
    Boolean getActive();
    String getPriorityName();
    String getTaskStatusName();
    String getReminderName();
    List<String> getSubtasks();
    List<String> getCategories();
    List<String> getTags();
}
