package model;

/**
 * The Room class is a regular room that isn't free and contains information like room number, price, and type.
 *
 * @author rachellai
 */

public class Room implements IRoom {
    private final String roomNumber;
    private final Double roomPrice;
    private final RoomType roomType;

    /**
     * Constructor
     *
     * @param roomNumber            string, room number
     * @param roomPrice             string, room price
     * @param roomType              string, room type (single/double)
     */
    public Room(String roomNumber, Double roomPrice, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public Boolean isFree() {
        return null;
    }

    /**
     *
     * @return          String of room data
     */
    @Override
    public String toString() {
        return "Room number: " + roomNumber + ", price: " + roomPrice +
                ", type: " + roomType + ".";
    }
}
