package kz.iitu.office.reservation.system.controller;


import kz.iitu.office.reservation.system.model.Room;
import kz.iitu.office.reservation.system.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public Room[] getAllRooms() {
        return roomService.getAllRooms();
    }

    @DeleteMapping("/admin/{id}")
    public void deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
    }

    @PostMapping("/admin/add")
    public void addRoom(@RequestBody Room room){roomService.addRoom(room);}
}
