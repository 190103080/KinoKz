package finalproject.springproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_cinema")
@Getter
@Setter
public class CinemaModel extends BaseEntity{

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
