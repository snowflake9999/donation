package internetdonation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "Donation_table")
public class Donation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long orderId;
	private String donorName;
	private Integer amt;
	private String status;

	@PostPersist
	public void onPostPersist() {
		System.out.println("***** 결재 요청 *****");

		if ("Ordered".equals(status)) {
			System.out.println("***** 결재 진행 *****");
			this.setStatus("Payed");
			PayCompleted payCompleted = new PayCompleted();
			BeanUtils.copyProperties(this, payCompleted);
			payCompleted.publish();

//            try {
//                Thread.currentThread().sleep((long) (400 + Math.random() * 220));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

			System.out.println(toString());
			System.out.println("***** 결재 완료 *****");

		} else if ("OrderCanceled".equals(status)) {
			System.out.println("***** 결재 취소 중 *****");
			
			this.setStatus("PayCanceled");
			PayCanceled payCancelled = new PayCanceled();
			BeanUtils.copyProperties(this, payCancelled);
			payCancelled.publish();
			
			System.out.println("***** 결재 취소 완료 *****");

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
