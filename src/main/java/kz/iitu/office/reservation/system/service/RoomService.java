package kz.iitu.office.reservation.system.service;

import kz.iitu.office.reservation.system.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RoomService {

    @Autowired
    RestTemplate restTemplate;

    public Room[] getAllRooms() {
        return restTemplate.getForEntity("http://localhost:8085/rooms", Room[].class).getBody();
    }

    public void deleteRoom(String id) {
        restTemplate.delete("http://localhost:8085/rooms/" + id);
    }

    public void addRoom(Room room) {
        restTemplate.postForEntity("http://localhost:8085/rooms/add", room, Room.class);
    }
}
