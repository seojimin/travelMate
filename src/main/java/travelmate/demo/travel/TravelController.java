package travelmate.demo.travel;

//import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@RestController
//@Autowired 대신 객체를 주입하는 방법인 생성자따로 적을 필요없는 Lombok annotation 그러나 인자의 순서가 바뀌어 error를 발생시킬 수 있음.
//@AllArgsConstructor
@RequestMapping("/travels")
public class TravelController {

    private final TravelRepository travelRepository;
    private final ModelMapper modelMapper;

    @Builder
    public TravelController(TravelRepository travelRepository, ModelMapper modelMapper){
        this.travelRepository = travelRepository;
        this.modelMapper = modelMapper;
    }

    //전체 travel - pagination(페이지 수 제한) PageRequest
    @GetMapping
    public ResponseEntity getAllTravel(@PathVariable Integer pNo){
        PageRequest pg = PageRequest.of(pNo-1, 20); //JPA 는 페이지가 0부터 존재. 0이 1번 페이
        List<Travel> travelList = travelRepository.findAllByOrderByIdDesc(pg); //Desc으로 최신부터 확인
        return ResponseEntity.ok().body(travelList);
    }

    //get single id
    @GetMapping("/travels/{id}")
    public ResponseEntity getTravel(@PathVariable String id){
        Travel travel = travelRepository.findById(Long.parseLong(id)).get();
        return ResponseEntity.ok().body(travel);
    }

    //save 사용자 여행 스타일 등록
    @PostMapping
    public ResponseEntity saveTravel(@RequestBody @Valid TravelDto travelDto, Errors errors){
        if (errors.hasErrors()){ return ResponseEntity.badRequest().body(errors); }

        Travel travel = modelMapper.map(travelDto, Travel.class);
        Travel createTravel = travelRepository.save(travel);

        return ResponseEntity.ok().body(createTravel);
    }

    //session
    private ResponseEntity hasPermission(String id, Errors errors, HttpSession session){
        //Session에서 값을 꺼내면 Object type으로 리턴
        //로그인이 안된 상태면 error발생
        Object tempUser = session.getAttribute("sessionUser");
        if(tempUser == null){ return ResponseEntity.badRequest().body(errors);}
        //로그인한 사용자의 요청이 아닌 다른 사용자의 요청이면 error발생
        Travel sessionUser = (Travel)tempUser;
        if(!id.equals(sessionUser.getId())){ return ResponseEntity.badRequest().body(errors); }

        return ResponseEntity.badRequest().body(errors);
    }

    //update - 로그인한 사용자만 update
    @PutMapping("/travels/{id}")
    public ResponseEntity updateTravel(@PathVariable String id, @RequestBody @Valid TravelDto travelDto, Errors errors, HttpSession session){
        if(errors.hasErrors()){ return ResponseEntity.badRequest().body(errors);}

        hasPermission(id, errors, session);

        Travel travel = travelRepository.findById(Long.parseLong(id)).get();
        //반복되는 set ,get 말고 modelMapper사용법은????
        travel.setTravelType(travelDto.getTravelType());
        travel.setCity(travelDto.getCity());
        travel.setCountry(travelDto.getCountry());
        travel.setStartDate(travelDto.getStartDate());
        travel.setEndDate(travelDto.getEndDate());
        travel.setStartTime(travelDto.getStartTime());
        travel.setEndTime(travelDto.getEndTime());
        travel.setAge(travelDto.getAge());
        travel.setGender(travelDto.getGender());

        Travel updateTravel = travelRepository.save(travel);
        return ResponseEntity.ok().body(updateTravel);
    }

    //delete - putmapping msg만 보이게. 작성자라면 삭제가능하게
    @DeleteMapping("/travels/{id}")
    public ResponseEntity deleteTravel(@PathVariable @Valid String id, Errors errors, HttpSession session){
        if(errors.hasErrors()){ return ResponseEntity.badRequest().body(errors);}
        hasPermission(id, errors, session);

        travelRepository.deleteById(Long.parseLong(id));

        // builder 라 build 가 무슨말이지????
        return ResponseEntity.noContent().build();
    }

    //Get date
    @GetMapping("/travels/date")       //@PathVariables 과 달리 URL이 아닌 URL에 담긴 값       //required = false => parameter없을때 null
    public ResponseEntity matchDate(@RequestParam(value = "startDate",required = false, defaultValue = "") Date startDate,
                                    @RequestParam(value = "endDate",required = false, defaultValue = "") Date endDate){
        List<Travel> travelDate = travelRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(startDate, endDate);
        return ResponseEntity.ok().body(travelDate);
    }

    //Get time
    @GetMapping("/travels/time")
    public ResponseEntity matchTime(@RequestParam(value = "startTime",required = false, defaultValue = "") Time startTime,
                                    @RequestParam(value = "endTime", required = false, defaultValue = "") Time endTime){
        List<Travel> travelTime = travelRepository.findAllByStartTimeLessThanEqualAndEndDateGreaterThanEqual(startTime, endTime);
        return ResponseEntity.ok().body(travelTime);
    }
}

//travel * from matches where startDate > today and endDate< 9999-12-31;
