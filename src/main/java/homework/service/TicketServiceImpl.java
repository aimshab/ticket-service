package homework.service;

import java.time.Instant;
import java.util.Optional;

import homework.manager.SeatHoldManager;
import homework.manager.SeatReservationManager;
import homework.model.SeatReservation;
import homework.model.Seat;
import homework.model.SeatHold;
import homework.model.SeatingLevel;
import homework.model.Venue;

public class TicketServiceImpl implements TicketService {

	private Venue venue;
	private SeatHoldManager seatHoldManager;
	private SeatReservationManager seatReservationManager;
	
	public TicketServiceImpl( Venue venue ) {
		this.venue = venue;
		this.seatHoldManager = new SeatHoldManager(1000);
		this.seatReservationManager = new SeatReservationManager(1000);
	}
	
	@Override
	public int numSeatsAvailable(Optional<Integer> venueLevel) {

		if (venueLevel.isPresent()) {
			SeatingLevel seatingLevel = venue.getSeatingLevels().get(venueLevel.get());
			if (seatingLevel == null) {
				return 0;
			}
			else {
				return seatingLevel.getAvailableSeatsCount();
			}
		}
		else {
			int count = 0;
			for (SeatingLevel seatingLevel :  venue.getSeatingLevels().values()){
				count += seatingLevel.getAvailableSeatsCount();
			}
			return count;
		}
	}

	@Override
	public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel,
			String customerEmail) {
		int startLevel = minLevel.orElse(venue.getSeatingLevels().firstKey());
		if (venue.getSeatingLevels().get(startLevel) == null) {
			return null;
		}
		
		int lastLevel = maxLevel.orElse(venue.getSeatingLevels().lastKey());
		if (venue.getSeatingLevels().get(lastLevel) == null) {
			return null;
		}
		
		if (startLevel > lastLevel) {
			return null;
		}
		
		SeatHold seatHold = new SeatHold();
		seatHold.setCustomerEmail(customerEmail);
		seatHold.setExpirationTime(Instant.now().plusMillis(SeatHold.MILLISECONDS_TO_HOLD));
		
		int seatsOnHoldCount = 0;
		for (int i = startLevel; i <= lastLevel && seatsOnHoldCount < numSeats; i++) {
			SeatingLevel seatingLevel = venue.getSeatingLevels().get( i );
			Seat seat = null;
			while (((seat = seatingLevel.getNextAvaliableSeat()) != null) && (seatsOnHoldCount < numSeats)) {
				if (seat.hold(seatHold)) {
					seatsOnHoldCount++;
				}
			}
		}
		
		if(seatHold.getSeatsOnHoldList().isEmpty()) {
			return null;
		}
		
		seatHold.setId(seatHoldManager.getNextId()) ;
		seatHoldManager.addSeatHold(seatHold);
		return seatHold;
	}

	@Override
	public String reserveSeats(int seatHoldId, String customerEmail) {
		SeatHold seatHold = seatHoldManager.getSeatHold(seatHoldId);
		if (seatHold != null ) {
			if ( ! seatHold.isExpired()) {
				if (seatHold.getCustomerEmail().equals(customerEmail)) {
					SeatReservation reservation = new SeatReservation() ;
					reservation.setCustomerEmail(customerEmail);
					reservation.setReservationCode(seatReservationManager.getNextReservationCode());
					reservation.setReservationTime(Instant.now());
					boolean status = true;
					for (Seat seat : seatHold.getSeatsOnHoldList()) {
						status &= seat.reserve(reservation);
					}
					if (status) {
						seatReservationManager.addSeatReservation(reservation);
						return "Reservation Code - " + reservation.getReservationCode();	
					}
					else {
						return "Cannot reserve all requested seats";
					}
				}
				else {
					return "Not matching customerEmail: " + customerEmail;
				}
			}
			else {
				return "Seat Hold Expired";
			}
		}
		return "Cannot find seat hold associated with id :" + seatHoldId;
	}

}
