package finalproject.springproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "t_reserved_seat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservedSeat extends BaseEntity{

    @OneToOne(mappedBy = "reservedSeat")
    private Ticket ticket;

}
