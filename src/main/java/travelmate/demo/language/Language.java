package travelmate.demo.language;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "english")
    private String english;
    @Column(name = "korean")
    private String korean;
    @Column(name = "chinese")
    private String chinese;
    @Column(name = "vietnamese")
    private String vietnamese;
    @Column(name = "french")
    private String french;
    @Column(name = "spanish")
    private String spanish;
}
