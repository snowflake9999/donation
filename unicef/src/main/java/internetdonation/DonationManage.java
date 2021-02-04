package internetdonation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

@Entity
@Table(name="DonationManage_table")
public class DonationManage {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String donorName;
    private Integer amt;
    private String status;

    @PostPersist
    public void onPostPersist(){
    	
        
        try {
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if("Payed".equals(status)) {
            this.setStatus("DonationCompleted");
            DonationCompleted donationCompleted = new DonationCompleted();
            BeanUtils.copyProperties(this, donationCompleted);
            donationCompleted.publish();

            System.out.println(toString());
         
        }


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
