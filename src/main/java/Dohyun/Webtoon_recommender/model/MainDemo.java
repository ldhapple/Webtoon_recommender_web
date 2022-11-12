package Dohyun.Webtoon_recommender.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MainDemo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String sex;

    @ManyToOne
    @JoinColumn(name = "webtoonid", referencedColumnName = "titleid")
    private WebtoonData webtoon;
}
