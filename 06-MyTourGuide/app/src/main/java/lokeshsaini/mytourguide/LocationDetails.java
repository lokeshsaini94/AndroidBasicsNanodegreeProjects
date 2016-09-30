package lokeshsaini.mytourguide;


public class LocationDetails {
    private String locationName;
    private String locationDesc;
    private int locationIcon;

    public LocationDetails() {
    }

    public LocationDetails(String locationName, String locationDesc, int locationIcon) {
        this.locationName = locationName;
        this.locationDesc = locationDesc;
        this.locationIcon = locationIcon;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public int getLocationIcon() {
        return this.locationIcon;
    }

    public String getLocationDesc() {
        return this.locationDesc;
    }


    @Override
    public String toString() {
        return locationName + " " + locationDesc + " " + locationIcon;
    }
}


