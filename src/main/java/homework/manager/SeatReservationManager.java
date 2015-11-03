package homework.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import homework.model.SeatReservation;

public class SeatReservationManager {

	private AtomicInteger idSequence;
	
	private Map<String, SeatReservation> seatReservations;
	
	public SeatReservationManager(int startIdValue) {
		seatReservations = new HashMap<String, SeatReservation>();
		idSequence = new AtomicInteger(startIdValue);
	}
	
	public String getNextReservationCode() {
		return Integer.toHexString(idSequence.getAndIncrement());
	}
	
	public void addSeatReservation(SeatReservation seatReservation) {
		seatReservations.put(seatReservation.getReservationCode(), seatReservation);
	}
	
	public SeatReservation getSeatReservation(String reservationCode) {
		return seatReservations.get(reservationCode);
	}
}
