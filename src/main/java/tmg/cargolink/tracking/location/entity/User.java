package tmg.cargolink.tracking.location.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@Table("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    @PrimaryKeyColumn(name = "id", type = PARTITIONED)
    private Long id;

    @PrimaryKeyColumn(name = "username", ordinal = 0)
    private String username;
    private String password;
    private String[] role;
}
