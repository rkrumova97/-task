package com.testtask.testtask.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;


@Data
@Builder
@AllArgsConstructor
@Entity(name = "account")
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Email
    @Column
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(columnDefinition = "DATE", name = "birth_date")
    private LocalDate birthday;
}
