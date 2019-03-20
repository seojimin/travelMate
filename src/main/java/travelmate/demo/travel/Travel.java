package travelmate.demo.travel;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
//@Data annotation 은 Setter의 남발. 사용지양
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
@Table(name = "travel")
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "startDate")
    private String startDate;
    @Column(name = "endDate")
    private String endDate;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private int city;
    @Column(name = "NumOfPeople")
    private String NumOfPeople;
    @Column(name = "age")
    private String age;
    @Column(name = "language")
    private String language;
    @Column(name = "gender")
    private String gender;
    @Column(name = "travelType")
    private String travelType;

}
