package com.sena.TaskManagement.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.TaskManagement.DTOs.HitoryWhitDetail;
import com.sena.TaskManagement.model.History;

public interface IHistory  extends JpaRepository<History, Integer>{

        @Query(value ="""
            SELECT
    h.id AS history_id,
    t.name,
    t.description,
    t.creation_date,
    t.expiration_date,
    t.active,
    p.name AS priority_name,
    ts.name AS task_status_name,
    r.name AS reminder_name,
    ARRAY_AGG(DISTINCT s.name) AS subtasks,
    ARRAY_AGG(DISTINCT c.name) AS categories,
    ARRAY_AGG(DISTINCT tg.name) AS tags
FROM
    history h
LEFT JOIN tasks t ON h.task_id = t.id
LEFT JOIN priorities p ON t.id_priority = p.id
LEFT JOIN task_status ts ON t.id_status = ts.id
LEFT JOIN reminders r ON r.task_id = t.id
LEFT JOIN subtasks s ON s.task_id = t.id
LEFT JOIN tasks_categories tc ON t.id = tc.task_id
LEFT JOIN categories c ON tc.category_id = c.id
LEFT JOIN tasks_tags tt ON t.id = tt.task_id
LEFT JOIN tags tg ON tt.tag_id = tg.id
GROUP BY
    h.id, t.id, t.name, t.description, t.creation_date, t.expiration_date,
    t.active, p.name, ts.name, r.name
ORDER BY
    t.id;
""", nativeQuery = true)
List<HitoryWhitDetail> findHitoryWhitDetail();
}
