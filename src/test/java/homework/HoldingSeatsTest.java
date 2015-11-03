package homework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;

import homework.model.Seat;
import homework.model.SeatHold;
import homework.model.Venue;
import homework.service.TicketService;
import homework.service.TicketServiceImpl;

public class HoldingSeatsTest {

	@Test
	public void testHoldOneSeatNoLevelPreference() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testHoldOneSeatNoLevelPreference", 1, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(4, availSeatCount);
		
		SeatHold seatHold = service.findAndHoldSeats(1, Optional.empty(), Optional.empty(), "john@amce.xyz") ;
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(3, availSeatCount);
		
		compareSeatHold(seatHold, 1000, "john@amce.xyz", 1);
		
		Seat seat = seatHold.getSeatsOnHoldList().get(0);
		compareSeat(seat, 1, 1, 1);
	}

	@Test
	public void testHoldOneSeatStartingFirstLevelPreference() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testHoldOneSeatStartingFirstLevelPreference", 1, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(4, availSeatCount);
		
		SeatHold seatHold = service.findAndHoldSeats(1, Optional.of(1), Optional.empty(), "john@amce.xyz") ;
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(3, availSeatCount);

		compareSeatHold(seatHold, 1000, "john@amce.xyz", 1);
		
		Seat seat = seatHold.getSeatsOnHoldList().get(0);
		compareSeat(seat, 1, 1, 1);
	}

	@Test
	public void testHoldOneSeatStartingLastLevelPreference() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testHoldOneSeatStartingLastLevelPreference", 3, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(12, availSeatCount);
		
		SeatHold seatHold = service.findAndHoldSeats(1, Optional.of(3), Optional.empty(), "john@amce.xyz") ;
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(11, availSeatCount);

		compareSeatHold(seatHold, 1000, "john@amce.xyz", 1);
		
		Seat seat = seatHold.getSeatsOnHoldList().get(0);
		compareSeat(seat, 1, 1, 3);
	}

	@Test
	public void testHoldOneSeatEndingLastLevelPreference() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testHoldOneSeatEndingLastLevelPreference", 3, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(12, availSeatCount);
		
		SeatHold seatHold = service.findAndHoldSeats(1, Optional.empty(), Optional.of(3), "john@amce.xyz") ;
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(11, availSeatCount);
		
		compareSeatHold(seatHold, 1000, "john@amce.xyz", 1);
		
		Seat seat = seatHold.getSeatsOnHoldList().get(0);
		compareSeat(seat, 1, 1, 1);
	}

	@Test
	public void testHoldOneSeatFullRangeLevelPreference() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testHoldOneSeatFullRangeLevelPreference", 3, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(12, availSeatCount);
		
		SeatHold seatHold = service.findAndHoldSeats(1, Optional.of(1), Optional.of(3), "john@amce.xyz") ;
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(11, availSeatCount);

		compareSeatHold(seatHold, 1000, "john@amce.xyz", 1);
		
		Seat seat = seatHold.getSeatsOnHoldList().get(0);
		compareSeat(seat, 1, 1, 1);
	}

	@Test
	public void testHoldOneSeatStartingMiddleRangeLevelPreference() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testHoldOneSeatStartingMiddleRangeLevelPreference", 3, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(12, availSeatCount);
		
		SeatHold seatHold = service.findAndHoldSeats(1, Optional.of(2), Optional.of(3), "john@amce.xyz") ;
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(11, availSeatCount);

		compareSeatHold(seatHold, 1000, "john@amce.xyz", 1);
		
		Seat seat = seatHold.getSeatsOnHoldList().get(0);
		compareSeat(seat, 1, 1, 2);
	}

	@Test
	public void testHoldSeatAllOneLevelPreference() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testHoldSeatAllOneLevelPreference", 1, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(4, availSeatCount);
		
		SeatHold seatHold = service.findAndHoldSeats(4, Optional.empty(), Optional.empty(), "john@amce.xyz") ;
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(0, availSeatCount);
		
		compareSeatHold(seatHold, 1000, "john@amce.xyz", 4);
		
		Seat seat = seatHold.getSeatsOnHoldList().get(0);
		compareSeat(seat, 1, 1, 1);
		
		seat = seatHold.getSeatsOnHoldList().get(1);
		compareSeat(seat, 2, 1, 1);

		seat = seatHold.getSeatsOnHoldList().get(2);
		compareSeat(seat, 1, 2, 1);
		
		seat = seatHold.getSeatsOnHoldList().get(3);
		compareSeat(seat, 2, 2, 1);
	}

	@Test
	public void testHoldSeatAllCrossLevelsPreference() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testHoldSeatAllCrossLevelsPreference", 2, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(8, availSeatCount);
		
		SeatHold seatHold = service.findAndHoldSeats(8, Optional.empty(), Optional.empty(), "john@amce.xyz") ;
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(0, availSeatCount);
		
		compareSeatHold(seatHold, 1000, "john@amce.xyz", 8);
		
		Seat seat = seatHold.getSeatsOnHoldList().get(0);
		compareSeat(seat, 1, 1, 1);
		seat = seatHold.getSeatsOnHoldList().get(1);
		compareSeat(seat, 2, 1, 1);
		seat = seatHold.getSeatsOnHoldList().get(2);
		compareSeat(seat, 1, 2, 1);
		seat = seatHold.getSeatsOnHoldList().get(3);
		compareSeat(seat, 2, 2, 1);
		
		seat = seatHold.getSeatsOnHoldList().get(4);
		compareSeat(seat, 1, 1, 2);
		seat = seatHold.getSeatsOnHoldList().get(5);
		compareSeat(seat, 2, 1, 2);
		seat = seatHold.getSeatsOnHoldList().get(6);
		compareSeat(seat, 1, 2, 2);
		seat = seatHold.getSeatsOnHoldList().get(7);
		compareSeat(seat, 2, 2, 2);
	}

	@Test
	public void testHoldSeatPartCrossLevelsPreference() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testHoldSeatPartCrossLevelsPreference", 2, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(8, availSeatCount);
		
		SeatHold seatHold = service.findAndHoldSeats(6, Optional.empty(), Optional.empty(), "john@amce.xyz") ;
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(2, availSeatCount);
		
		compareSeatHold(seatHold, 1000, "john@amce.xyz", 6);
		
		Seat seat = seatHold.getSeatsOnHoldList().get(0);
		compareSeat(seat, 1, 1, 1);
		seat = seatHold.getSeatsOnHoldList().get(1);
		compareSeat(seat, 2, 1, 1);
		seat = seatHold.getSeatsOnHoldList().get(2);
		compareSeat(seat, 1, 2, 1);
		seat = seatHold.getSeatsOnHoldList().get(3);
		compareSeat(seat, 2, 2, 1);
		
		seat = seatHold.getSeatsOnHoldList().get(4);
		compareSeat(seat, 1, 1, 2);
		seat = seatHold.getSeatsOnHoldList().get(5);
		compareSeat(seat, 2, 1, 2);
	}

	@Test
	public void testHoldOneSeatExpire() throws InterruptedException { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testHoldOneSeatExpire", 1, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(4, availSeatCount);
		
		SeatHold seatHold = service.findAndHoldSeats(1, Optional.empty(), Optional.empty(), "john@amce.xyz") ;
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(3, availSeatCount);
		
		compareSeatHold(seatHold, 1000, "john@amce.xyz", 1);
		
		Seat seat = seatHold.getSeatsOnHoldList().get(0);
		compareSeat(seat, 1, 1, 1);
		
		Thread.sleep(SeatHold.MILLISECONDS_TO_HOLD + 10);
		
		assertTrue(seat.isAvailable());

		//Reserve Again should be able to reserve the same seat
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(4, availSeatCount);
		seatHold = service.findAndHoldSeats(1, Optional.empty(), Optional.empty(), "john@amce.xyz") ;
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(3, availSeatCount);
		
		//The SeatHold id should have been incremented
		compareSeatHold(seatHold, 1001, "john@amce.xyz", 1);
		
		seat = seatHold.getSeatsOnHoldList().get(0);
		compareSeat(seat, 1, 1, 1);
	}

	@Test
	public void testHoldTwoSeatsSeparately() throws InterruptedException { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testHoldTwoSeatsSeparately", 1, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(4, availSeatCount);
		
		SeatHold seatHold = service.findAndHoldSeats(1, Optional.empty(), Optional.empty(), "john@amce.xyz") ;
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(3, availSeatCount);
		
		compareSeatHold(seatHold, 1000, "john@amce.xyz", 1);
		
		Seat seat = seatHold.getSeatsOnHoldList().get(0);
		compareSeat(seat, 1, 1, 1);

		//Reserve another one should be different than the first one (if time is not delayed)
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(3, availSeatCount);
		seatHold = service.findAndHoldSeats(1, Optional.empty(), Optional.empty(), "john@amce.xyz") ;
		availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(2, availSeatCount);
		
		//The SeatHold id should have been incremented
		compareSeatHold(seatHold, 1001, "john@amce.xyz", 1);
		
		seat = seatHold.getSeatsOnHoldList().get(0);
		compareSeat(seat, 2, 1, 1);
	}

	private void compareSeatHold( SeatHold seatHold, int expectedId, String expectedCustomerEmail, int expectedNumberOfSeats ) {
		assertNotNull(seatHold);
		assertEquals(expectedCustomerEmail, seatHold.getCustomerEmail());
		assertNotNull(seatHold.getExpirationTime());
		assertEquals(expectedId, seatHold.getId());
		assertNotNull(seatHold.getSeatsOnHoldList());
		assertEquals(expectedNumberOfSeats, seatHold.getSeatsOnHoldList().size());
				
	}
	private void compareSeat( Seat seat, int expectedSeatNumber, int expectedRowNumber, int expectedLevel ) {
		assertNotNull(seat);
		assertEquals(expectedSeatNumber, seat.getNumber());
		assertNotNull(seat.getSeatRow());
		assertEquals(expectedRowNumber, seat.getSeatRow().getNumber());
		assertNotNull(seat.getSeatRow().getSeatingLevel());
		assertEquals(expectedLevel, seat.getSeatRow().getSeatingLevel().getLevel());
		assertEquals("Level - " + expectedLevel, seat.getSeatRow().getSeatingLevel().getName());
		
	}
}
