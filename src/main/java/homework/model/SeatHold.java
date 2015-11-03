package homework.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SeatHold {

	private int id;
	private String customerEmail;
	private Instant expirationTime;
	private List<Seat> seatsOnHoldList;
	
	public static final long MILLISECONDS_TO_HOLD = 5000;
	
	public SeatHold() {
		seatsOnHoldList = new ArrayList<Seat>() ;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}
	
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	public Instant getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Instant expirationTime) {
		this.expirationTime = expirationTime;
	}

	public boolean isExpired() {		
		return expirationTime != null ? Instant.now().isAfter(expirationTime) : true;
	}
	
	public List<Seat> getSeatsOnHoldList() {
		return seatsOnHoldList;
	}

}
