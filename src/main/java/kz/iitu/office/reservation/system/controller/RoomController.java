package kz.iitu.office.reservation.system.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import kz.iitu.office.reservation.system.model.Room;
import kz.iitu.office.reservation.system.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    @HystrixCommand(fallbackMethod = "getAllRoomsFallback")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @DeleteMapping("/admin/{id}")
    public void deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
    }

    @PostMapping("/admin/add")
    public void addRoom(@RequestBody Room room){roomService.addRoom(room);}

    public List<Room> getAllRoomsFallback() {
        List<Room> list = new ArrayList<>();
        Room r = new Room();
        r.setNumber("-1");
        r.setReserves(new ArrayList<>());
        list.add(r);
        return list;
    }
}
