
// Abstract Base Class
abstract class Vehicle {
    private String vehicleId;
    private String model;
    private double baseRentalRate;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String model, double baseRentalRate) {
        Objects.requireNonNull(vehicleId, "Vehicle ID cannot be null");
        Objects.requireNonNull(model, "Model cannot be null");
        if (baseRentalRate <= 0) {
            throw new IllegalArgumentException("Base rental rate must be greater than zero.");

        }
        this.vehicleId = vehicleId;
        this.model = model;
        this.baseRentalRate = baseRentalRate;
        this.isAvailable = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public double getBaseRentalRate() {
        return baseRentalRate;
    }
    public boolean isAvailable() { 
        return isAvailable;
     }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public abstract double calculateRentalCost(int days);

    public abstract boolean isAvailableForRental();
}

// Rentable Interface
interface Rentable {
    void rent(Customer customer, int days);
    void returnVehicle();
}

// Concrete Vehicle Classes
class Car extends Vehicle implements Rentable {
    private double baseRentalRate;
    private static final double DISCOUNT_RATE = 0.75;

    public Car(String vehicleId, String model, double baseRentalRate) {
        super(vehicleId, model, baseRentalRate);
        this.baseRentalRate = baseRentalRate;
    }

    public double getBaseRentalRate() {
        return baseRentalRate;
    }

    public void setBaseRentalRate(double baseRentalRate) {
        if (baseRentalRate <= 0) {
            throw new IllegalArgumentException("Base rental rate must be positive.");
        }
        this.baseRentalRate = baseRate;
    }

    @Override
    public double calculateRentalCost(int days) {
        return baseRentalRate * days;
        return days > 7 ? cost * DISCOUNT_RATE : cost;
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }

    @Override
    public void rent(Customer customer, int days) {
        if (!isAvailable()) {
            throw new IllegalStateException("Car is not available for rental.");
        }
        setAvailable(false);
        System.out.println("Car rented to " + customer.getName() + " for " + days + " days.");
    }

    @Override
    public void returnVehicle() {
        setAvailable(true);
        System.out.println("Car returned.");
    }
}

class Motorcycle extends Vehicle implements Rentable {
    private double baseRentalRate;
    private static final double DISCOUNT_RATE = 0.25;

    public Car(String vehicleId, String model, double baseRentalRate) {
        super(vehicleId, model, baseRentalRate);
        this.baseRentalRate = baseRentalRate;
    }

    public double getBaseRentalRate() {
        return baseRentalRate;
    }

    public void setBaseRentalRate(double baseRentalRate) {
        if (baseRentalRate <= 0) {
            throw new IllegalArgumentException("Base rental rate must be positive.");
        }
        this.baseRentalRate = baseRate;
    }

    @Override
    public double calculateRentalCost(int days) {
        return baseRentalRate * days;
        return days > 7 ? cost * DISCOUNT_RATE : cost;
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }

    @Override
    public void rent(Customer customer, int days) {
        if (!isAvailable()) {
            throw new IllegalStateException("Motorcycle is not available for rental.");
        }
        setAvailable(false);
        System.out.println("Motorcycle rented to " + customer.getName() + " for " + days + " days.");
    }

    @Override
    public void returnVehicle() {
        setAvailable(true);
        System.out.println("Motorcycle returned.");
    }private double baseRentalRate;

   
}

class Truck extends Vehicle implements Rentable {
    private double baserentalRate;
    private static final double DISCOUNT_RATE = 0.85;
    private double weightLimit;

    public Truck(String vehicleId,String model. double baseRentalRate, double weightLimit) {
        super(vehicleId, model, baseRentalRate);
        this.baseRentalRate = baseRentalRate;
        this.weightLimit = weightLimit;
    }

    public double getBaseRentalRate() {
        return baseRentalRate;
    }

    public void setBaseRentalRate(double baseRentalRate) {
        if (baseRentalRate <= 0) {
            throw new IllegalArgumentException("Base rate must be positive.");
        }
        this.baseRentalRate = baseRentalRate;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(double weightLimit) {
        if (weightLimit <= 0) {
            throw new IllegalArgumentException("Weight limit must be positive.");
        }
        this.weightLimit = weightLimit;
    }

    @Override
    public double calculateRentalCost(int days) {
        return baseRentalRate * days + (weightLimit * 0.1);
    }

    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }

    @Override
    public void rent(Customer customer, int days) {
        if (!isAvailable()) {
            throw new IllegalStateException("Truck is not available for rental.");
        }
        setAvailable(false);
        System.out.println("Truck rented to " + customer.getName() + " for " + days + " days.");
    }

    @Override
    public void returnVehicle() {
        setAvailable(true);
        System.out.println("Truck returned.");
    }
}

// Supporting Classes
class Customer {
    private String name;
    private String contactInfo;

    public Customer(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}

class RentalAgency {
    private List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle findAvailableVehicle(Class<? extends Vehicle> type) {
        for (Vehicle vehicle : vehicles) {
            if (type.isInstance(vehicle) && vehicle.isAvailableForRental()) {
                return vehicle;
            }
        }
        return null;
    }
}

class RentalTransaction {
    private Customer customer;
    private Vehicle vehicle;
    private int rentalDays;
    private double totalCost;

    public RentalTransaction(Customer customer, Vehicle vehicle, int rentalDays) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.totalCost = vehicle.calculateRentalCost(rentalDays);
    }

    public void printReceipt() {
        System.out.println("Rental Receipt:");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Vehicle: " + vehicle.getLicensePlate());
        System.out.println("Days: " + rentalDays);
        System.out.println("Total Cost: $" + totalCost);
    }
}

//Main Class
public class VehicleRentalManagementSystem {
    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();
        Vehicle car = new Car("L101", "Lexus RX 350", 72);
        Customer customer = new Customer("C01", "Kiekie");

        agency.addVehicle(car);
        agency.listAvailableVehicles();

        ((Rentable) car).rent(customer, 5);
        agency.listAvailableVehicles();
        ((Rentable) car).returnVehicle();
    }
}
