package kz.iitu.office.reservation.system.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import kz.iitu.office.reservation.system.model.ReservedRooms;
import kz.iitu.office.reservation.system.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(
            fallbackMethod = "getAllRoomsFallback",
            threadPoolKey = "getAllRooms",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50")
            }
    )
    public List<Room> getAllRooms() {
        return restTemplate.exchange("http://localhost:8085/rooms", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Room>>() {}).getBody();
    }

    public void deleteRoom(String id) {
        restTemplate.delete("http://localhost:8085/rooms/" + id);
    }

    public void addRoom(Room room) {
        restTemplate.postForEntity("http://localhost:8085/rooms/add", room, Room.class);
    }

    public List<Room> getAllRoomsFallback() {
        List<Room> list = new ArrayList<>();
        Room r = new Room();
        r.setNumber("-1");
        r.setReserves(new ArrayList<>());
        list.add(r);
        return list;
    }
}
