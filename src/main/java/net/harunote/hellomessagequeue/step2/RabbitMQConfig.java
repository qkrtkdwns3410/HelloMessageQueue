package net.harunote.hellomessagequeue.step2;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // 큐 네임을 설정한다.
    public static final String QUEUE_NAME = "WorkQueue";

    @Bean
    public Queue queue() {
        // true: 큐가 영속성을 가지고 있으며, 서버가 종료되거나 재시작될 때 큐의 메시지가 사라지지 않습니다.
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO); // 메시지 처리 후 자동으로 확인(ACK)을 합니다. 디폴트는 AUTO 입니다.

        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(WorkQueueConsumer workQueueConsumer) {
        return new MessageListenerAdapter(workQueueConsumer, "workQueueTask");
    }

}
