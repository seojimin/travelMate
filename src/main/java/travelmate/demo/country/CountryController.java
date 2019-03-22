package travelmate.demo.country;

import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryRepository countryRepository;

    @Builder
    public CountryController(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    @GetMapping
    public ResponseEntity getAllCountry(@PathVariable Integer pNo){
        PageRequest pg = PageRequest.of(pNo-1, 10);
        List<Country> countryList = countryRepository.findAllByOrderByIdDesc(pg);
        return ResponseEntity.ok().body(countryList);
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity getCountry(@PathVariable String id){
        Country country = countryRepository.findById(Long.parseLong(id)).get();
        return ResponseEntity.ok().body(country);
    }

}

