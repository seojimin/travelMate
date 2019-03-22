package travelmate.demo.country;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CountryDto {
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
