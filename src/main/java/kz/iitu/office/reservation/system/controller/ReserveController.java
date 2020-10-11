package kz.iitu.office.reservation.system.controller;

import kz.iitu.office.reservation.system.model.ReservedRooms;
import kz.iitu.office.reservation.system.service.ReservedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/reserves")
public class ReserveController {

    private final ReservedService reservedService;

    @Autowired
    public ReserveController(ReservedService reservedService) {
        this.reservedService = reservedService;
    }

    @GetMapping
    public ReservedRooms[] getAllResRoom() {
        return reservedService.getAllResRoom();
    }

    @PostMapping("/add")
    public void addReserve(@RequestBody ReservedRooms reserve) {
        reservedService.addReserve(reserve);
    }

    @DeleteMapping("/delete/{id}")
    public void removeReserve(@PathVariable Long id) {
        reservedService.removeReserve(id);
    }

    @PutMapping("/update/{id}")
    public void updateMember(@PathVariable Long id, @RequestBody ReservedRooms reserve) {
        reserve.setId(id);
        reservedService.updateReserve(id, reserve);
    }

    @GetMapping("/{num}")
    public ReservedRooms[] getReserveById(@PathVariable String num) {
        return reservedService.getReservesByRoom(num);
    }

}
