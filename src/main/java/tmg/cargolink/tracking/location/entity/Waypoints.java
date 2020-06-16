package tmg.cargolink.tracking.location.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import tmg.cargolink.tracking.location.util.DateHandler;
import tmg.cargolink.tracking.location.util.TimestampHandler;

import java.util.Date;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Waypoints {
    @PrimaryKeyColumn(type = PARTITIONED)
    @JsonDeserialize(using = DateHandler.class)
    @JsonSerialize(using = TimestampHandler.class)
    private Date stime;
    @PrimaryKeyColumn(ordinal = 0)
    private String vehicle;
    @PrimaryKeyColumn(ordinal = 1)
    private Double latitude;
    @PrimaryKeyColumn(name = "provider_id", ordinal = 2)
    private Long providerId;
    private String driver;
    private Double heading;
    private Double longitude;
    private Double speed;
    private Long version;
    @Column("created_at")
    private String createdAt;
    @Column("updated_at")
    private String updatedAt;
    @Column("created_user")
    private String createdUser;
    @Column("updated_user")
    private String updatedUser;
    @Column("is_deleted")
    private String isDeleted;
}
