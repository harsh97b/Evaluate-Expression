public class DataStored {

    private final double result;
    private final String region;
    private final String country;
    DataStored(double result, String region, String country){
        this.result = result;
        this.region = region;
        this.country = country;
    }

    public double getResult(){
        return this.result;
    }
    public String getRegion(){
        return this.region;
    }
    public String getCountry(){
        return this.country;
    }
    record RegionCountry(String region, String country){};
}
