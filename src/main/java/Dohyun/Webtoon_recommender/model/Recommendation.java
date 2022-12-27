package Dohyun.Webtoon_recommender.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "webtoonid", referencedColumnName = "titleid")
    private WebtoonData webtoon;

    private Long rank;
}
