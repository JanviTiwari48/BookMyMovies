package service;

import config.DataBaseConfig;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class BookMyMovieSys {

    Scanner sc = new Scanner(System.in);

    // Display movies
    public void displayMovies() {
        try {
            Connection con = DataBaseConfig.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from movies");

            System.out.println("--------Available Movies------");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("movie_id") + ". "
                                + rs.getString("title")
                                + " (" + rs.getString("genre") + ")"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Show theatres in a city
    public void displayTheaters(String city) {
        try {
            Connection con = DataBaseConfig.getConnection();

            PreparedStatement stmt =
                    con.prepareStatement("select * from theaters where city=?");

            stmt.setString(1, city);

            ResultSet rs = stmt.executeQuery();

            System.out.println("Theaters in " + city + ":");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("theater_id") + ". "
                                + rs.getString("name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Display shows
    public void displayShows(int movieId, int theaterId) {
        try {
            Connection con = DataBaseConfig.getConnection();

            PreparedStatement stmt = con.prepareStatement(
                    "select * from shows where movie_id=? AND theater_id=?"
            );

            stmt.setInt(1, movieId);
            stmt.setInt(2, theaterId);

            ResultSet rs = stmt.executeQuery();

            System.out.println("Available Shows");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("show_id") + ". "
                                + rs.getString("timing")
                                + " | Seats Available: "
                                + rs.getInt("available_seats")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Book ticket
    public void bookTicket(int userId, int showId, List<String> selectedSeats) {

        try {
            Connection con = DataBaseConfig.getConnection();
            con.setAutoCommit(false);

            boolean alreadyBookedSeat = false;

            // Check if seats exist and are available
            for (String seat : selectedSeats) {

                PreparedStatement stmt = con.prepareStatement(
                        "select * from seat where seat_number=? and show_id=?"
                );

                stmt.setString(1, seat);
                stmt.setInt(2, showId);

                ResultSet rs = stmt.executeQuery();

                if (!rs.next()) {
                    alreadyBookedSeat = true;
                    System.out.println("Seat " + seat + " does not exist!");
                } else if (rs.getBoolean("is_booked")) {
                    alreadyBookedSeat = true;
                    System.out.println("Seat " + seat + " is already booked!");
                }
            }

            if (alreadyBookedSeat) {
                System.out.println("Booking Failed! Some seats are unavailable.");
                con.rollback();
                return;
            }

            // Mark seats as booked
            for (String seat : selectedSeats) {

                PreparedStatement stmt = con.prepareStatement(
                        "update seat set is_booked=TRUE where seat_number=? and show_id=?"
                );

                stmt.setString(1, seat);
                stmt.setInt(2, showId);

                stmt.executeUpdate();
            }

            // Update available seats in shows table
            PreparedStatement seatUpdateStmt = con.prepareStatement(
                    "update shows set available_seats = available_seats - ? where show_id=?"
            );

            seatUpdateStmt.setInt(1, selectedSeats.size());
            seatUpdateStmt.setInt(2, showId);

            seatUpdateStmt.executeUpdate();

            // Calculate total price
            double seatPrice = 200.0;
            double totalPrice = selectedSeats.size() * seatPrice;

            // Insert booking record
            PreparedStatement bookingStmt = con.prepareStatement(
                    "insert into bookings(user_id,show_id,seats_booked,total_price) values(?,?,?,?)"
            );

            bookingStmt.setInt(1, userId);
            bookingStmt.setInt(2, showId);
            bookingStmt.setString(3, String.join(",", selectedSeats));
            bookingStmt.setDouble(4, totalPrice);

            bookingStmt.executeUpdate();

            con.commit();

            System.out.println(
                    "Booking Successful! Seats: "
                            + selectedSeats
                            + " | Total Price: "
                            + totalPrice
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}