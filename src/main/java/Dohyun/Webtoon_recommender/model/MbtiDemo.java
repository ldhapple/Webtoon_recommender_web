package Dohyun.Webtoon_recommender.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MbtiDemo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mbti;

    @ManyToOne
    @JoinColumn(name = "webtoonid", referencedColumnName = "titleid")
    private WebtoonData webtoon;
}
