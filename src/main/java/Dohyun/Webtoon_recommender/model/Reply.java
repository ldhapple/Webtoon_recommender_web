package Dohyun.Webtoon_recommender.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 120)
    String content;

    @ManyToOne
    @JoinColumn(name="titleid")
    private WebtoonData webtoon;

    @ManyToOne
    @JoinColumn(name="userid")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;
}
