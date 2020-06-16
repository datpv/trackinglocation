package tmg.cargolink.tracking.location.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Arrays;

@Slf4j
public class CommonUtils implements Serializable {

  /**
   * Cast object to object
   *
   * @param object : object before
   * @param tClass : object after
   * @return : object
   * @author : Datpv
   */
  public static <T> T castObjectToObject(Object object, Class<T> tClass) {
    // List<VehicleResDto> vehicleResDtoList = Arrays.asList(mapper.readValue(jsonStr, VehicleResDto[].class));
    return new Gson().fromJson(CommonUtils.convertJsonToString(object), tClass);
  }

  /**
   * Convert json to string
   *
   * @param object json
   * @return string
   * @author Datpv
   */
  private static String convertJsonToString(Object object) {
    return new Gson().toJson(object);
  }


  public static JsonObject castJsonStringToJsonObject(String jsonString) {
    JsonParser parser = new JsonParser();
    return (JsonObject) parser.parse(jsonString);
  }

  public static <T> T castJsonStringToOject(Object object, Class<T> tClass) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(object.toString(), tClass);
    } catch (JsonProcessingException e) {
      log.error("Exception", e);
    }
    return null;
  }

  public static String castOjectToJson(Object object) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      log.error("Exception", e);
    }
    return null;
  }

  public static <T> T castJsonStringToOjectList(Object object, Class<T[]> tClass) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return (T) Arrays.asList(mapper.readValue(object.toString(), tClass));
    } catch (JsonProcessingException e) {
      log.error("Exception", e);
    }
    return null;
  }

  public static String compareStringMax(String s1, String s2) {
    if (s1.compareTo(s2) >= 0) {
      return s1;
    } else return s2;
  }

  public static String compareStringMin(String s1, String s2) {
    if (s1.compareTo(s2) <= 0) {
      return s1;
    } else return s2;
  }

  // cast JsonOject List to Class
  //ObjectMapper mapper = new ObjectMapper();
// List<VehicleResDto> vehicleResDtoList = Arrays.asList(mapper.readValue(jsonStr, VehicleResDto[].class));
}
