package br.insper.events.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventsController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventService.listEvents();
    }
}
