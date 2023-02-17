package finalproject.springproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_kinoteatry")
@Getter
@Setter
public class Kinoteatry extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "kiImage")
    private String kiImage;

}
