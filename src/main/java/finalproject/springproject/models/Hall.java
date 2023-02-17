package finalproject.springproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "t_hall")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hall extends BaseEntity{

    @OneToMany
    private List<Seat> seats;

}
