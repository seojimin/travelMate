package travelmate.demo.city;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "toronto")
    private String toronto;
    @Column(name = "vancouver")
    private String vancouver;
    @Column(name = "seoul")
    private String seoul;
    @Column(name = "busan")
    private String busan;
    @Column(name = "shanghai")
    private String shanghai;
    @Column(name = "beijing")
    private String beijing;
    @Column(name = "newyork")
    private String newyork;
    @Column(name = "losAngeles")
    private String losAngeles;
    @Column(name = "hanoi")
    private String hanoi;
}
