package homework.manager;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import homework.model.SeatHold;

public class SeatHoldManager {

	private AtomicInteger idSequence;
	
	private TreeMap<Integer, SeatHold> seatHolds;
	
	public SeatHoldManager(int startIdValue) {
		seatHolds = new TreeMap<Integer, SeatHold>();
		idSequence = new AtomicInteger(startIdValue);
	}
	
	public int getNextId() {
		return idSequence.getAndIncrement();
	}
	
	public void addSeatHold(SeatHold seatHold) {
		seatHolds.put(seatHold.getId(), seatHold);
	}
	
	public SeatHold getSeatHold( int id ) {
		return seatHolds.get(id);
	}
}
