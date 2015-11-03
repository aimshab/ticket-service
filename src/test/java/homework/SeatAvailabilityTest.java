package homework;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;

import homework.model.Venue;
import homework.service.TicketService;
import homework.service.TicketServiceImpl;

public class SeatAvailabilityTest {

	@Test
	public void testAllAvailableOneLevel() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testAllAvailable", 1, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(4, availSeatCount);	
	}
	
	@Test
	public void testAllAvailableTwoLevels() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testAllAvailableTwoLevels", 2, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.empty());
		assertEquals(8, availSeatCount);	
	}

	@Test
	public void testAllAvailablePerLevelFirst() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testAllAvailablePerLevelFirst", 3, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.of(1));
		assertEquals(4, availSeatCount);	
	}

	@Test
	public void testAllAvailablePerLevelLast() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testAllAvailablePerLevelLast", 3, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.of(3));
		assertEquals(4, availSeatCount);	
	}

	@Test
	public void testAllAvailablePerLevelOutOfRange() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testAllAvailablePerLevelOutOfRange", 3, new BigDecimal("120.15"), 2, 2) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.of(7));
		assertEquals(0, availSeatCount);	
	}

	@Test
	public void testAllAvailableNoSeats() { 
		VenueGenerator venueGenerator = new VenueGenerator() ;
	
		Venue venue = venueGenerator.generateVenue("testAllAvailableNoSeats", 3, new BigDecimal("120.15"), 2, 0) ;
		
		TicketService service = new TicketServiceImpl(venue);
		int availSeatCount = service.numSeatsAvailable(Optional.of(7));
		assertEquals(0, availSeatCount);	
	}

}
