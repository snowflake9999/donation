package internetdonation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import internetdonation.config.kafka.KafkaProcessor;

@Service
public class MypageViewHandler {


    @Autowired
    private MypageRepository repository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1 (@Payload Ordered ordered) {
        try {
            if (ordered.isMe()) {
                // view 객체 생성
                 Mypage mypage = new Mypage();
                // view 객체에 이벤트의 Value 를 set 함
                 mypage.setId		(ordered.getId());
                 mypage.setOrderId	(ordered.getOrderId());
                 mypage.setDonorName(ordered.getDonorName());
                 mypage.setStatus	(ordered.getStatus());
                // view 레파지 토리에 save
                 repository.save(mypage);
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
                List<Mypage> mypageList = repository.findByOrderId(payCompleted.getOrderId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                	mypage.setStatus(payCompleted.getStatus());
                    // view 레파지 토리에 save
                    repository.save(mypage);
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
                List<Mypage> mypageList = repository.findByOrderId(donationCompleted.getOrderId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                	mypage.setStatus(donationCompleted.getStatus());
                    // view 레파지 토리에 save
                    repository.save(mypage);
                }
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
                List<Mypage> mypageList = repository.findByOrderId(payCanceled.getOrderId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                	mypage.setStatus(payCanceled.getStatus());
                    // view 레파지 토리에 save
                    repository.save(mypage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}