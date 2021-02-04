package internetdonation;

import internetdonation.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MypageViewHandler {


    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1 (@Payload Ordered ordered) {
        try {
            if (ordered.isMe()) {
                // view 객체 생성
                  = new ();
                // view 객체에 이벤트의 Value 를 set 함
                .setOrderId(.getId());
                .setOrderStatus(.getStatus());
                .setQty(.getQty());
                .setDonorName(.getDonorName());
                // view 레파지 토리에 save
                Repository.save();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayCompleted_then_UPDATE_1(@Payload PayCompleted payCompleted) {
        try {
            if (payCompleted.isMe()) {
                // view 객체 조회
                List<> List = Repository.findByStatus(.getStatus());
                for(  : List){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    // view 레파지 토리에 save
                    Repository.save();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDonationCompleted_then_UPDATE_2(@Payload DonationCompleted donationCompleted) {
        try {
            if (donationCompleted.isMe()) {
                // view 객체 조회
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayCanceled_then_DELETE_1(@Payload PayCanceled payCanceled) {
        try {
            if (payCanceled.isMe()) {
                // view 레파지 토리에 삭제 쿼리
                Repository.deleteByOrderId(.getOrderId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}