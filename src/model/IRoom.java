package model;

/**
 *
 * IRoom is a room that, given room number, price, and type, an admin can create in the database and a customer can book.
 *
 * @author rachellai
 */

public interface IRoom {
    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public Boolean isFree();

}
