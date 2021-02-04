package internetdonation;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import internetdonation.external.Donation;
import internetdonation.external.DonationService;


import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String donorName;
    private Integer amt;
    private String status;

    @PostPersist
    public void onPostPersist(){
    	
    	this.setStatus("Oredered");
    	
        Ordered ordered = new Ordered();
        ordered.setId(this.id);
        ordered.setOrderId(this.orderId);
        ordered.setDonorName(this.donorName);
        ordered.setAmt(this.amt);
        ordered.setStatus(this.status);
        
        BeanUtils.copyProperties(this, ordered);
        ordered.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

       Donation donation = new Donation();
       donation.setOrderId(this.getOrderId());
       donation.setDonorName(this.donorName);
       donation.setStatus(this.status);
       donation.setAmt(this.amt);
        // mappings goes here
        OrderApplication.applicationContext.getBean(DonationService.class)
            .pay(donation);


    }

    @PostUpdate
    public void onPostUpdate(){
//        OrderCanceled orderCanceled = new OrderCanceled();
//        BeanUtils.copyProperties(this, orderCanceled);
//        orderCanceled.publishAfterCommit();


    }
    
    @PostRemove
    public void onPostRemove(){
        this.setStatus("OrderCanceled");
        OrderCanceled orderCancelled = new OrderCanceled();
        BeanUtils.copyProperties(this, orderCancelled);
        orderCancelled.publish();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }
    public Integer getAmt() {
        return amt;
    }

    public void setAmt(Integer amt) {
        this.amt = amt;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
