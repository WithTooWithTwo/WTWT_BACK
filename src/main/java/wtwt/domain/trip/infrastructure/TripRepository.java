package wtwt.domain.trip.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wtwt.domain.trip.model.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

}
