package tmg.cargolink.tracking.location.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tmg.cargolink.tracking.location.entity.Waypoints;
import tmg.cargolink.tracking.location.repository.WaypointsRepository;
import tmg.cargolink.tracking.location.util.CommonUtils;

@Service
@Slf4j
public class ConsumerKafka {

    @Autowired
    WaypointsRepository waypointsRepository;

    @KafkaListener(topics = "#{'${spring.kafka.consumer.topic}'.split(',')}", groupId = "#{'${spring.kafka.consumer.group-id}'}")
    public void consume(String message) {
        log.info(String.format("#### -> Consumed message -> %s", message));
        waypointsRepository.save(CommonUtils.castJsonStringToOject(message, Waypoints.class));
    }
}
