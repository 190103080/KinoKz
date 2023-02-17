package finalproject.springproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "t_session")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session extends BaseEntity{

    @Column(name = "time")
    private String time;

    @Column(name = "data")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    @Column(name = "language")
    private String language;

//    @Column(name = "cost")
//    private int cost;

    @ManyToOne(fetch = FetchType.EAGER)
    private Kinoteatry kinoteatry;

    @OneToOne(fetch = FetchType.EAGER)
    private CinemaModel cinemaModel;

    @ManyToOne
    private Hall hall;

}
