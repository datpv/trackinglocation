package tmg.cargolink.tracking.location.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import tmg.cargolink.tracking.location.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CassandraRepository<User, UUID> {
    @AllowFiltering
    Optional<User> findByUsernameAndPassword(String username, String password);

    @AllowFiltering
    Optional<User> findByUsername(String username);

    Optional<User> findById(Long userId);


    @Query("select * from user where username = ?0 ALLOW FILTERING")
    Optional<User> findByFname(String username);
}
