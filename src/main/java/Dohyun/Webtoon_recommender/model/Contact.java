package Dohyun.Webtoon_recommender.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @NotNull
    @NotBlank
    String email;

    @NotNull
    @NotBlank
    String title;

    @NotNull
    @NotBlank
    @Column(nullable = false, length = 120)
    String content;
}
