package com.fitareq.noteme;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "note")
public class DataClass {


    @PrimaryKey
    @NonNull
    private Long id;
    private String taskName;
    private String createdDate;
    private String deadline;
    private String status;
    private String description;
    private String email;
    private String phone;
    private String url;


    public DataClass(@NonNull Long id, String taskName, String createdDate, String deadline, String status, String description, String email, String phone, String url) {
        this.id = id;
        this.taskName = taskName;
        this.createdDate = createdDate;
        this.deadline = deadline;
        this.status = status;
        this.description = description;
        this.email = email;
        this.phone = phone;
        this.url = url;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUrl() {
        return url;
    }
}
