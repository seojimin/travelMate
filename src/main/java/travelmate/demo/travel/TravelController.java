package travelmate.demo.travel;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/travels")
public class TravelController {

    private final TravelRepository travelRepository;

/*    public MatchController(TravelRepository travelRepository){
        this.travelRepository = travelRepository;
    }*/

    //전체매칭  getMatch - 페이지네이션(한번에 전부 나오면 안된다)
    @GetMapping
    public ResponseEntity getAllTravels(){
        List<Travel> travelsList = travelRepository.findAll();
        return ResponseEntity.ok().body(travelsList);
    }

    //id하나만 get
    @GetMapping("/{id}")
    public ResponseEntity getTravel(@PathVariable String id){
        Travel travel = travelRepository.findById(Long.parseLong(id)).get();
        return ResponseEntity.ok().body(travel);
    }

    //postMatch 매치된 여행등록
//    @PostMapping
//    public ResponseEntity saveTravel(@RequestBody @Valid TravelDto travelDto, Errors errors){
//
//    }


    //Get 핉터된 부분이 나오는 메서드.
    public ResponseEntity getMatchesByDate(@RequestParam(value = "startDate",required = false, defaultValue = "") Date startDate, @RequestParam Date endDate){
        travelRepository.findByStartDateBefore(startDate, endDate); //List 받아오기
        return null;
    }

    //put - update

    //delete - putmapping msg만 보이게. 작성자라면 삭제가능하게


}

//travel * from matches where startDate > today and endDate< 9999-12-31;
   /* private Long id;
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
    private String travelType;*/