package travelmate.demo.travel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Setter @Getter
@NoArgsConstructor
public class TravelDto {

    @NotEmpty
    @Column(name = "startDate")
    private String startDate;
    @NotEmpty
    @Column(name = "endDate")
    private String endDate;
    @NotEmpty
    @Column(name = "country")
    private String country;
    @NotEmpty
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
