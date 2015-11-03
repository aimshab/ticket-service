package homework.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SeatReservation {

	private String customerEmail;
	private Instant reservationTime;
	private String reservationCode;
	private List<Seat> reservedSeats;
	
	public SeatReservation() {
		reservedSeats = new ArrayList<Seat>();
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	public Instant getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(Instant reservationTime) {
		this.reservationTime = reservationTime;
	}

	public String getReservationCode() {
		return reservationCode;
	}

	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	}

	public List<Seat> getReservedSeats() {
		return reservedSeats;
	}

}
