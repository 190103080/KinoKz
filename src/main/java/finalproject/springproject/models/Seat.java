package finalproject.springproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_seat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat extends BaseEntity{

    @Column(name = "seat")
    private String seat;

}
