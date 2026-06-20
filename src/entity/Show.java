package entity;

public class Show {

    private int showId;
    private int movieId;
    private int theatreId;
    private String timing;
    private int availableSeats;

    public Show(int showId, int movieId, int theatreId, String timing, int availableSeats) {
        this.showId = showId;
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.timing = timing;
        this.availableSeats = availableSeats;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
