package Dohyun.Webtoon_recommender.model;

import lombok.Data;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class WebtoonData {

    @Id
    private long titleid; // title_num

    private String titlename; // 제목
    private String author; // 작가
    private String genre; // 장르
    private String story; // 줄거리
    private String link; // 웹툰사이트 링크
    private String img_src; // 이미지 소스

    @OneToMany(mappedBy = "webtoon")
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "webtoon")
    private List<Reply> reply;
}
