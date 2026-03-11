package br.insper.events.event;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class QueueService {

    @Autowired
    private RedisTemplate<String, Event> redisTemplate;

    @Autowired
    private EventService eventService;

    private static final String QUEUE_KEY = "events-queue";

    private boolean running = true;

    @PostConstruct
    public void startWorker() {
        new Thread(this::processQueue).start();
    }

    private void processQueue() {
        while (running) {

            Event event = redisTemplate.opsForList().leftPop(QUEUE_KEY, Duration.ofSeconds(5));
            if (event != null) {

                System.out.println("Processando order" + event.getSource());
                eventService.saveEvent(event);
            } else {
                System.out.println("Fila vazia, aguardando...");
            }
        }
    }

    @PreDestroy
    public void stop() {
        running = false;
    }

}
