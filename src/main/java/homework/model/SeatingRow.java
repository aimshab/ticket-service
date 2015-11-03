package homework.model;

import java.util.ArrayList;
import java.util.List;

public class SeatingRow {

	private int number;
	
	private SeatingLevel seatingLevel ;
	private List<Seat> seats;
	
	public SeatingRow() {
		seats = new ArrayList<Seat>();
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public SeatingLevel getSeatingLevel() {
		return seatingLevel;
	}

	public void setSeatingLevel(SeatingLevel seatingLevel) {
		this.seatingLevel = seatingLevel;
	}

	public List<Seat> getSeats() {
		return seats;
	}
	
	public int getAvailableSeatsCount() {
		int count = 0;
		for ( Seat seat : getSeats()) {
			if ( seat.isAvailable() ) {
				count++;
			}
		}
		return count; 
	}
	
	public Seat getNextAvaliableSeat() {
		for ( Seat seat : getSeats()) {
			if ( seat.isAvailable() ) {
				return seat;
			}
		}
		return null;
	}
}
