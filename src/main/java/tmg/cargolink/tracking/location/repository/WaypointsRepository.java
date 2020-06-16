package tmg.cargolink.tracking.location.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import tmg.cargolink.tracking.location.entity.Waypoints;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WaypointsRepository extends CassandraRepository <Waypoints, Date>{

    @AllowFiltering
    Optional<List<Waypoints>> findByVehicle(String vehicle);
}
