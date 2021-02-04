package internetdonation;

import internetdonation.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDonationCompleted_(@Payload DonationCompleted donationCompleted){

        if(donationCompleted.isMe()){
            System.out.println("##### listener  : " + donationCompleted.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayCanceled_(@Payload PayCanceled payCanceled){

        if(payCanceled.isMe()){
            System.out.println("##### listener  : " + payCanceled.toJson());
        }
    }

}
