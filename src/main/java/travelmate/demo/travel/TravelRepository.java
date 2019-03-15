package travelmate.demo.travel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    List<Travel> findByStartDateBefore(Date startDate, Date endDate);
}
