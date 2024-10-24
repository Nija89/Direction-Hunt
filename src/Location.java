import java.util.HashMap;
import java.util.Map;

class Location{
    private int locationId;
    private String locationDescription;
    private Map<String, Integer> exits = new HashMap<>();

    public Location(String locationDescription, Map<String, Integer> locationCollection) {
        this.locationDescription = locationDescription;
        this.exits = locationCollection;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public Map<String, Integer> getExits() {
        return new HashMap<>(exits);
    }
}