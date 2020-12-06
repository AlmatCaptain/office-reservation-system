package kz.iitu.office.reservation.system.service;

import kz.iitu.office.reservation.system.model.ReservedRooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "reserve-room-topic";

    @Autowired
    KafkaTemplate<String, ReservedRooms> kafkaTemplate;

    public String sendReserveRequest(ReservedRooms reserve) {
        this.kafkaTemplate.send(TOPIC, reserve);
        return "Successfully";
    }
}
