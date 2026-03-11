package br.insper.events.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> listEvents() {
        return eventRepository.findAll();
    }

    public Event saveEvent(Event event) {
        event.setDate(LocalDateTime.now());
        return eventRepository.save(event);
    }

}
