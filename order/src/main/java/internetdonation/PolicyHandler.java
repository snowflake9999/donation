package internetdonation;

import internetdonation.config.kafka.KafkaProcessor;
import phoneseller.Order;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
	
	@Autowired
	OrderRepository repository;
	
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){
    	

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDonationCompleted_(@Payload DonationCompleted donationCompleted){

        if(donationCompleted.isMe()){
            System.out.println("##### listener  : " + donationCompleted.toJson());
            
            Optional<Order> orderOptional= repository.findById(donationCompleted.getOrderId());
            Order order = orderOptional.get();
            order.setStatus("DonationCompleted");
            
            repository.save(order);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayCanceled_(@Payload PayCanceled payCanceled){

        if(payCanceled.isMe()){
            System.out.println("##### listener  : " + payCanceled.toJson());
            
            
            Optional<Order> orderOptional= repository.findById(payCanceled.getOrderId());
            Order order = orderOptional.get();
            order.setStatus("PayCancelled");
            repository.save(order);
        }
    }

}
