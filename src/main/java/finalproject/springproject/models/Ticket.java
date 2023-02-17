package finalproject.springproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_ticket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket extends BaseEntity{

    @Column(name = "cost")
    private int cost;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reserved_seat_id", referencedColumnName = "id")
    private ReservedSeat reservedSeat;

    @ManyToOne
    private Session session;

    @ManyToOne
    private User user;

}
