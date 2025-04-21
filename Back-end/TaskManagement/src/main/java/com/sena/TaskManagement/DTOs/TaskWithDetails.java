package com.sena.TaskManagement.DTOs;

import java.time.LocalDate;
import java.util.List;

public interface TaskWithDetails {
    int getId();

    String getName();

    String getDescription();

    LocalDate getCreationDate();

    LocalDate getExpirationDate();

    boolean getActive();

    String getPriorityName();

    String getTaskStatusName();

    String getReminderName();

    String getSubtaskName();

    List<String> getCategories();

    List<String> getTags();
}