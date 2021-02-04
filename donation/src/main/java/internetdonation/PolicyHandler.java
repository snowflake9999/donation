package internetdonation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import internetdonation.config.kafka.KafkaProcessor;

@Service
public class PolicyHandler{
	
	
	@Autowired
	DonationRepository repository;
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_(@Payload OrderCanceled orderCanceled){

        if(orderCanceled.isMe()){
            System.out.println("##### listener  : " + orderCanceled.toJson());

            Donation donation = new Donation();
            donation.setOrderId(orderCanceled.getId());
            donation.setStatus("OrderCancelled");
            
            repository.save(donation);
        }
    }

}
