package homework;

import java.math.BigDecimal;

import homework.model.Seat;
import homework.model.SeatingLevel;
import homework.model.SeatingRow;
import homework.model.Venue;

public class VenueGenerator {

	public Venue generateVenue( String venueName, int numberOfLevels, BigDecimal levelBasePrice, int rowsPerLevel, int seatsPerRow) {
		Venue venue = new Venue() ;
		venue.setName(venueName);
		for(int level = 1; level <= numberOfLevels; level++) {
			SeatingLevel seatingLevel = new SeatingLevel();
			seatingLevel.setLevel(level);
			seatingLevel.setName("Level - " + level);
			seatingLevel.setPrice(levelBasePrice.multiply(new BigDecimal(level)));
			seatingLevel.setVenue(venue);
			venue.getSeatingLevels().put(seatingLevel.getLevel(), seatingLevel);
			
			for(int row = 1; row <= rowsPerLevel; row++) {
				SeatingRow seatingRow = new SeatingRow();
				seatingRow.setNumber(row);
				seatingRow.setSeatingLevel(seatingLevel);
				seatingLevel.getSeatRows().add(seatingRow);
				
				for(int seatCount = 1; seatCount <= seatsPerRow; seatCount++){
					Seat seat = new Seat();
					seat.setNumber(seatCount);
					seat.setSeatRow(seatingRow);
					seatingRow.getSeats().add(seat);
				}
			}
		}
		return venue;
	}
}
