package Movie;

/**
 * Implementation of MovieADT to create instances of Movie
 *
 * @author Fantastic4
 * @version 0.0
 */
public class Movie implements movieADT {

    private final String name;
    private final boolean type;
    private final String genre;
    private final String releaseDate;
    private boolean availability;
    private int quantity;
    
    public Movie(String name, boolean type, String genre, String releaseDate, boolean availability, int quantity) {
        this.name = name;
        this.type = type;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.availability = availability;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Boolean getType() {
        return this.type;
    }

    @Override
    public String getGenre() {
        return this.genre;
    }

    @Override
    public String getReleaseDate() {
        return this.releaseDate;
    }

    @Override
    public Boolean getAvailability() {
        return this.availability;
    }

    @Override
    public void setAvailability(boolean truth) throws Exception {
        this.availability = truth;
    }

    @Override
    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public void setQuantity(int quantity) throws Exception {
        if (quantity < 0) {
            throw new Exception("Cannot have negative or 0 quanity.");
        } else {
            this.quantity = quantity;
        }
    }

    @Override
    public String toString() {
        String tempType;
        if (this.type) {
            tempType = "BluRay";
        } else {
            tempType = "DVD";
        }

        return String.format("Name: %-12s\t Type: %-6s\t Genre: %-6s\t Release Date: %-10s\t Quanity: %d\t",
                name, tempType, genre, releaseDate, quantity);
    }

    @Override
    public Object[] toRow(int i) {
        String tempType;
        Object[] results = null;

        if (this.type) {
            tempType = "BluRay";
        } else {
            tempType = "DVD";
        }

        switch (i) {
            case 0:
                results = new Object[]{name, genre, releaseDate, tempType, "", quantity};
                break;
            case 1:
                results = new Object[]{name, genre, releaseDate, tempType, ""};
                break;
            case 2:
                results = new Object[]{name, genre, releaseDate, tempType};
                break;
        }

        return results;

    }
}
