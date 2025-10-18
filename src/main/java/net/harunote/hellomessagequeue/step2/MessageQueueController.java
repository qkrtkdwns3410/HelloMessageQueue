package net.harunote.hellomessagequeue.step2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageQueueController {
    private final WorkQueueProducer workQueueProducer;

    public MessageQueueController(WorkQueueProducer workQueueProducer) {
        this.workQueueProducer = workQueueProducer;
    }

    @PostMapping("/workqueue")
    public String workQueue(@RequestParam String message, @RequestParam int duration) {
        workQueueProducer.sendWorkQueue(message, duration);
        return "[#] WorkQueue sent successfully! " + message + " Duration: " + duration;
        /**
         * curl -X POST "http://localhost:8080/api/workqueue?message=Hello&duration=1000"
         * 
         * curl -X POST "http://localhost:8080/api/workqueue?message=Hello&duration=2000"
         * 
         * curl -X POST "http://localhost:8080/api/workqueue?message=Hello&duration=3000"
         * 
         * curl -X POST "http://localhost:8080/api/workqueue?message=Hello&duration=4000"
         * 
         * curl -X POST "http://localhost:8080/api/workqueue?message=Hello&duration=5000"
         */
    }
}
