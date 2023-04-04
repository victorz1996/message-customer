package co.com.testrabbit.messagelistener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
public class MessageListener {
    @RabbitListener(queues = "${broker.queueName}")
    public void receiveMessage(Message message){
        log.info("*Llega la correspondencia para todos los pisos*");
    }

    @RabbitListener(queues = "${broker.queueNameImpares}")
    public void receiveMessage2(Message message){
        log.info("*LLega la correspondencia para los pisos impares*");
    }
}
