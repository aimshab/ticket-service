package homework.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SeatingLevel {

	
	private int level;
	private String name;
	private BigDecimal price;
	
	private Venue venue;
	private List<SeatingRow> seatRows;
	
	public SeatingLevel() {
		seatRows = new ArrayList<SeatingRow>();
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public List<SeatingRow> getSeatRows() {
		return seatRows;
	}

	public void setSeatRows(List<SeatingRow> seatRows) {
		this.seatRows = seatRows;
	}
	
	public int getAvailableSeatsCount() {
		int count = 0;
		for ( SeatingRow seatingRow : getSeatRows()) {
			count += seatingRow.getAvailableSeatsCount();
		}
		return count;
		
	}
	
	public Seat getNextAvaliableSeat() {
		for ( SeatingRow seatingRow : getSeatRows()) {
			Seat seat = seatingRow.getNextAvaliableSeat();
			if ( seat != null ) {
				return seat;
			}
		}
		return null;
	}
}
