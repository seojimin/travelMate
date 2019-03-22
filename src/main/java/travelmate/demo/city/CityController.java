package travelmate.demo.city;

import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    private CityRepository cityRepository;

    @Builder
    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping
    public ResponseEntity getAllCity(@PathVariable Integer pNo) {
        PageRequest pg = PageRequest.of(pNo - 1, 10);
        List<City> cityList = cityRepository.findAllByOrderByIdDesc(pg);
        return ResponseEntity.ok().body(cityList);
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity getCountry(@PathVariable String id) {
        City city = cityRepository.findById(Long.parseLong(id)).get();
        return ResponseEntity.ok().body(city);
    }
}