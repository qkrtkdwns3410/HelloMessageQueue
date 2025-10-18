package net.harunote.hellomessagequeue.step2;

import org.springframework.stereotype.Component;

@Component
public class WorkQueueConsumer {
    public void workQueueTask(String message) {
        String[] messageParts = message.split("\\|");
        String originMessage = messageParts[0];
        int duration = Integer.parseInt(messageParts[1].trim());

        System.out.println("# Received: Origin Message: " + originMessage + " Duration: " + duration);
        try {
            int seconds = duration / 1000;
            for (int i = 0; i < seconds; i++) {
                System.out.println("# Processing: Origin Message: " + originMessage + " Duration: " + duration
                        + " Progress: " + (i + 1) + "/" + seconds);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[#] Completed: Origin Message: " + originMessage + " Duration: " + duration);
    }
}
