package com.butzevka.driverapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String trainingTitle;
    @OneToMany
    private Set<Question> trainingQuestions;
}
