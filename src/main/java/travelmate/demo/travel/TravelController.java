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
    @GetMapping("/list")
    public ResponseEntity getAllTravel(@PathVariable Integer pNo){
        PageRequest pg = PageRequest.of(pNo-1, 20); //JPA 는 페이지가 0부터 존재. 0이 1번 페이
        List<Travel> travelList = travelRepository.findAllByOrderByIdDesc(pg); // Desc으로 최신부터 확인
        return ResponseEntity.ok().body(travelList);
    }

    //id하나만 get
    @GetMapping("/{id}")
    public ResponseEntity getTravel(@PathVariable String id){
        Travel travel = travelRepository.findById(Long.parseLong(id)).get();
        return ResponseEntity.ok().body(travel);
    }

    //Get 핉터된 부분이 나오는 메서드
    @GetMapping
    public ResponseEntity getMatchByDate(@RequestParam(value = "startDate",required = false, defaultValue = "") Date startDate, @RequestParam Date endDate){
        travelRepository.findByStartDateBefore(startDate, endDate); //List 받아오기
        return null;
    }

    //save 사용자 여행 스타일 등록
    @PostMapping
    public ResponseEntity saveTravel(@RequestBody @Valid TravelDto travelDto, Errors errors){
        if (errors.hasErrors()){ return ResponseEntity.badRequest().body(errors); }

        Travel travel = modelMapper.map(travelDto, Travel.class);
        Travel createTravel = travelRepository.save(travel);

        return ResponseEntity.ok().body(createTravel);
    }

    private ResponseEntity hasPermission(String id, Errors errors, HttpSession session){
        //Session에서 값을 꺼내면 Object type으로 리턴
        //로그인이 안된 상태면 error발생
        Object tempUser = session.getAttribute("sessionUser");
        if(tempUser == null){ return ResponseEntity.badRequest().body(errors);}
        //로그인한 사용자의 요청이 아닌 다른 사용자의 요청이면 error발생
        Travel sessionUser = (Travel)tempUser;
        if(!id.equals(sessionUser.getId())){ return ResponseEntity.badRequest().body(errors); }
        // 리턴이 이런식이어도 가능한가????
        return ResponseEntity.badRequest().body(errors);
    }

    //update - 로그인한 사용자만 update
    @PutMapping("/{id}")
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
        travel.setAge(travelDto.getAge());
        travel.setGender(travelDto.getGender());

        Travel updateTravel = travelRepository.save(travel);
        return ResponseEntity.ok().body(updateTravel);
    }

    //delete - putmapping msg만 보이게. 작성자라면 삭제가능하게
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTravel(@PathVariable @Valid String id, Errors errors, HttpSession session){
        if(errors.hasErrors()){ return ResponseEntity.badRequest().body(errors);}
        hasPermission(id, errors, session);

        travelRepository.deleteById(Long.parseLong(id));

        // builder 라 build 가 무슨말이지????
        return ResponseEntity.noContent().build();
    }

}

//travel * from matches where startDate > today and endDate< 9999-12-31;
