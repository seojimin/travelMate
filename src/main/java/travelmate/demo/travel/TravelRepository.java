package travelmate.demo.travel;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    //pagination
    List<Travel> findAllByOrderByIdDesc(PageRequest page);

    //check date
    List<Travel> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date startDate, Date endDate);

    //check time
    List<Travel> findAllByStartTimeLessThanEqualAndEndDateGreaterThanEqual(Time startTime, Time endTime);

    //
}
