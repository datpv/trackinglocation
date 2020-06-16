package tmg.cargolink.tracking.location.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tmg.cargolink.tracking.location.entity.Waypoints;
import tmg.cargolink.tracking.location.repository.WaypointsRepository;
import tmg.cargolink.tracking.location.service.ProducerKafka;
import tmg.cargolink.tracking.location.util.CommonUtils;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest")
@ApiOperation(value = "Find student by id")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = String.class),
        @ApiResponse(code = 422, message = "Student not found"),
        @ApiResponse(code = 417, message = "Exception failed")
})
public class KafkaController {

    @Autowired
    WaypointsRepository waypointsRepository;

    private final ProducerKafka producer;
    @Autowired
    KafkaController(ProducerKafka producer) {
        this.producer = producer;
    }

    @RequestMapping(value = "/kafka/publish", method = RequestMethod.POST)
    public void sendMessageToKafkaTopic(@RequestBody String message) {
        this.producer.sendMessage(message);
    }

    @RequestMapping(value = "/kafka/getLocation", method = RequestMethod.POST)
    public ResponseEntity<String> getLocation(@RequestBody String vehicle) {
        Optional<List<Waypoints>> waypointsList =  waypointsRepository.findByVehicle(vehicle);
        if(!waypointsList.isPresent() || waypointsList.get().size() == 0)
            return new ResponseEntity<String>("empty", HttpStatus.OK);
        return new ResponseEntity<String>(CommonUtils.castOjectToJson(waypointsList.get()), HttpStatus.OK);
    }

}
