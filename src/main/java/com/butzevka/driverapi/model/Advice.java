package com.butzevka.driverapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Advice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String adviceTitle;
    private String multimediaSrc;
    private String adviceText;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tags;
    private boolean adviceOfTheWeek;
    private Long likes;
    private Long shares;
    @OneToOne
    private Training training;


}