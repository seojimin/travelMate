package travelmate.demo.language;


import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {
    private final LanguageRepository languageRepository;

    @Builder
    public LanguageController(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @GetMapping
    public ResponseEntity getAllCountry(@PathVariable Integer pNo) {
        PageRequest pg = PageRequest.of(pNo - 1, 10);
        List<Language> countryList = languageRepository.findAllByOrderByIdDesc(pg);
        return ResponseEntity.ok().body(countryList);
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity getCountry(@PathVariable String id) {
        Language language = languageRepository.findById(Long.parseLong(id)).get();
        return ResponseEntity.ok().body(language);
    }

}
