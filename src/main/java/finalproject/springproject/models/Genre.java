package finalproject.springproject.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_genre")
@Data

public class Genre extends BaseEntity{

    @Column(name = "kind")
    private String kind;

}
