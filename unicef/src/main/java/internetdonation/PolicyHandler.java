package internetdonation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import internetdonation.config.kafka.KafkaProcessor;

@Service
public class PolicyHandler{
	
	
	@Autowired
	DonationManageRepository repository;
	
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayCompleted_(@Payload PayCompleted payCompleted){

        if(payCompleted.isMe()){
            System.out.println("##### listener  : " + payCompleted.toJson());
            
            System.out.println("##### listener OrderReceive : " + payCompleted.toJson());
            System.out.println("store_policy_paycompleted_orderreceive");

            DonationManage donationmanage = new DonationManage();
            donationmanage.setOrderId(payCompleted.getOrderId());
            donationmanage.setStatus("Payed");
            repository.save(donationmanage);
        }
    }

}
