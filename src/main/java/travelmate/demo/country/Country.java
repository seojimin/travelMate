package travelmate.demo.country;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "canada")
    private String canada;
    @Column(name = "southKorea")
    private String southKorea;
    @Column(name = "usa")
    private String usa;
    @Column(name = "china")
    private String china;
    @Column(name = "vietnam")
    private String vietnam;
}
