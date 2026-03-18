package com.inspire.user.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDate;

@Entity
@Table(name = "PersonalEvent")
@Getter
public class PersonalEventEntity {
    @Id
    @Column(name = "personal_event_id")
    private Long personalEventId;

    private String title;
    private LocalDate date;
    private String type; // study, exam, deadline, other
    private String description;
    
    @Column(name = "user_id")
    private Long userId;
}