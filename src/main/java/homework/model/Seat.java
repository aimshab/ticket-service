package homework.model;

public class Seat {
	
	private int number;
	
	private SeatingRow seatRow;
	private SeatHold seatHold;
	private SeatReservation reservation;
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public SeatingRow getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(SeatingRow seatRow) {
		this.seatRow = seatRow;
	}

	public boolean isAvailable() {
		if ( ( ( seatHold != null ) && ( ! seatHold.isExpired() ) ) || ( reservation != null ) ){
			return false;
		}
		else {
			return true;
		}
	}
	
	public synchronized boolean hold(SeatHold seatHold) {
		if ( isAvailable() ) {
			this.seatHold = seatHold ;
			seatHold.getSeatsOnHoldList().add( this ) ;
			return true ;
		}
		else {
			return false ;
		}
	}
	
	public synchronized boolean reserve(SeatReservation reservation) {
		if (this.reservation == null) {
			this.seatHold = null;
			this.reservation = reservation ;
			reservation.getReservedSeats().add( this ) ;
			return true ;
		}
		return false;
	}
	
	public void clear() {
		this.seatHold = null;
		this.reservation = null;
	}
}
