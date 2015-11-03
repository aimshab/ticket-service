package homework;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;

import homework.model.SeatHold;
import homework.model.Venue;
import homework.service.TicketService;
import homework.service.TicketServiceImpl;

public class ReservingSeatsTest {

	@Test
	public void testReserveSeatHold() throws InterruptedException { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testReserveSeatHold", 1, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		
		SeatHold seatHold = service.findAndHoldSeats(2, Optional.empty(), Optional.empty(), "john@amce.xyz") ;
		assertNotNull(seatHold);
		
		String code = service.reserveSeats(seatHold.getId(), "john@amce.xyz");
		assertNotNull(code);
		assertTrue(code.startsWith("Reservation Code - "));
	}

	@Test
	public void testReserveExpiredSeatHold() throws InterruptedException { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testReserveExpiredSeatHold", 1, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		
		SeatHold seatHold = service.findAndHoldSeats(2, Optional.empty(), Optional.empty(), "john@amce.xyz") ;
		assertNotNull(seatHold);
		
		Thread.sleep(SeatHold.MILLISECONDS_TO_HOLD + 10);
		
		String code = service.reserveSeats(seatHold.getId(), "john@amce.xyz");
		assertNotNull(code);
		assertTrue(code.equals("Seat Hold Expired"));
	}

	@Test
	public void testReserveIncorrectSeatHoldId() throws InterruptedException { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testReserveIncorrectSeatHoldId", 1, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		
		SeatHold seatHold = service.findAndHoldSeats(2, Optional.empty(), Optional.empty(), "john@amce.xyz") ;
		assertNotNull(seatHold);
		
		String code = service.reserveSeats(12442, "john@amce.xyz");
		assertNotNull(code);
		assertTrue(code.startsWith("Cannot find seat hold associated with id "));
	}

	@Test
	public void testReserveIncorrectCustomerEmail() throws InterruptedException { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testReserveIncorrectSeatHoldId", 1, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		
		SeatHold seatHold = service.findAndHoldSeats(2, Optional.empty(), Optional.empty(), "john@amce.xyz") ;
		assertNotNull(seatHold);
		
		String code = service.reserveSeats(seatHold.getId(), "ksjjshjh");
		assertNotNull(code);
		assertTrue(code.startsWith("Not matching customerEmail"));
	}

}
