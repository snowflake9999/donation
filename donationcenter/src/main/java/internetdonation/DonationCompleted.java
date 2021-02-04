package internetdonation;

public class DonationCompleted extends AbstractEvent {

	 private Long id;
	    private Long orderId;
	    private String donorName;
	    private Integer amt;
	    private String status;

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