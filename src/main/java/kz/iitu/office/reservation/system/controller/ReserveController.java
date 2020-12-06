package kz.iitu.office.reservation.system.controller;

import kz.iitu.office.reservation.system.model.ReservedRooms;
import kz.iitu.office.reservation.system.service.KafkaProducerService;
import kz.iitu.office.reservation.system.service.ReservedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserves")
public class ReserveController {

    private final ReservedService reservedService;
    private final KafkaProducerService producerService;

    @Autowired
    public ReserveController(ReservedService reservedService, KafkaProducerService producerService) {
        this.reservedService = reservedService;
        this.producerService = producerService;
    }

    @GetMapping
    public List<ReservedRooms> getAllResRoom() {
        return reservedService.getAllResRoom();
    }

    @PostMapping("/add")
    public void addReserve(@RequestBody ReservedRooms reserve) {
        this.producerService.sendReserveRequest(reserve);
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
    public List<ReservedRooms> getReserveById(@PathVariable String num) {
        return reservedService.getReservesByRoom(num);
    }

}
