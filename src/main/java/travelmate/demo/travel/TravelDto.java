package travelmate.demo.travel;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //parameter가 없는 생성자를 만들어 준다. AccessLevel 로는 낮은 접근지시자를 이용.
@AllArgsConstructor //@Setter 대신 사용. 모든 fields를 parameter 로 받는 생성자
public class TravelDto {

    //@NotNull 과 다르게 null과 "" 둘 다 허용하지 않
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
