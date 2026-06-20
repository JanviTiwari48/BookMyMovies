package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookMyMovieApp {
    public static void main(String[] args) {
        BookMyMovieSys mbs = new BookMyMovieSys();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter City:");
        String city = sc.next();
        mbs.displayTheaters(city);

        System.out.println("Enter Theater Id and Movie Id:");
        int theaterId = sc.nextInt();
        int movieId = sc.nextInt();
        mbs.displayShows(movieId, theaterId);

        System.out.println("Enter Show Id:");
        int showId = sc.nextInt();

        System.out.println("Enter number of seats:");
        int n = sc.nextInt();

        List<String> seats = new ArrayList<>();

        System.out.println("Enter seat numbers:");
        for (int i = 0; i < n; i++) {
            seats.add(sc.next());
        }

        mbs.bookTicket(1, showId, seats);
    }
}