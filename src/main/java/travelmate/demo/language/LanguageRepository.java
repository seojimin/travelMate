package travelmate.demo.language;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language,Long> {

    List<Language> findAllByOrderByIdDesc(PageRequest page);
}
