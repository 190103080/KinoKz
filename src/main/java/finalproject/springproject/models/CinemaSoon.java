package finalproject.springproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_cinemasoon")
@Getter
@Setter
public class CinemaSoon extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "duration")
    private String duration;

    @Column(name = "releaseYear")
    private String releaseYear;

    @Column(name = "rating")
    private double rating;

    @Column(name = "image")
    private String image;

}
