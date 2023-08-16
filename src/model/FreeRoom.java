package model;

/**
 * FreeRoom is a room that is free of charge
 */

public class FreeRoom extends Room {
    /**
     * Constructor
     *
     * @param roomNumber
     * @param roomType
     */
    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, 0.0, roomType);
    }

    /**
     *
     * @return          String of free room data similar to that of a regular room
     */
    @Override
    public String toString() {
        return "Free of charge; " + super.toString();
    }
}
