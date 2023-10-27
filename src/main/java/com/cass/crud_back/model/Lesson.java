package com.cass.crud_back.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 12, nullable = false)
    private String youtubeUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    /* specifies that the associated entity should be fetched lazily (only when accessed)
    and that it's a mandatory relationship (the associated entity must exist, and it cannot be null).  */
    @JoinColumn(name = "course_id", nullable = false) //foreign key
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //avoids circular dependency (Course <-> Lesson)
    private Course course;
}
