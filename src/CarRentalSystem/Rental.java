package CarRentalSystem;

import java.time.LocalDate;

public class Rental {
    private String licensePlate;
    private String carType;
    private String carColor;
    private String carManufacturer;
    private String carModel;
    private LocalDate dateAdded;
    private int fuelCapacity;
    private int modelYear;
    private int mileage;
//    Per day
    private int rate;

    public Rental(String licensePlate, String carType, String carColor, String carManufacturer, String carModel, LocalDate dateAdded, int fuelCapacity, int modelYear,  int mileage, int rate) {
        this.licensePlate = licensePlate;
        this.carType = carType;
        this.carColor = carColor;
        this.carManufacturer = carManufacturer;
        this.carModel = carModel;
        this.dateAdded = dateAdded;
        this.fuelCapacity = fuelCapacity;
        this.modelYear = modelYear;
        this.mileage = mileage;
        this.rate = rate;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    

}
