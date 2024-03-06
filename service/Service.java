package service;
import domain.Vehicle;
import domain.CarRental;
import domain.Customer;
import repository.*;

import java.io.FileReader;
import java.util.List;
import java.util.Properties;

public class Service {
    private RIdentifiable<Integer, Vehicle> vehicleRepository;
    private RentalsRepository rentalsRepository;
    private VehicleRepositoryBinaryFile vehicleRepositoryBinaryFile;
    private VehicleRepositoryTextFile vehicleRepositoryTextFile;
    private void initService() {
        try (FileReader fr = new FileReader("settings.properties")) {
            Properties properties = new Properties();
            properties.load(fr);
            String vehicleRepoType = properties.getProperty("vehicleRepoType");
            String filePath = properties.getProperty("Vehicles");
            switch (vehicleRepoType) {
                case "memory":
                    vehicleRepository = new VehicleRepository();
                    rentalsRepository = new RentalsRepository();
                    initVehicleRepo();
                    initCustomersAndRentals();
                    break;
                case "text":
                    vehicleRepository = new VehicleRepositoryTextFile(filePath);
                    rentalsRepository = new RentalsRepository();
                    initVehicleRepo();
                    initCustomersAndRentals();
                    break;
                case "binary":
                    vehicleRepository = new VehicleRepositoryBinaryFile(filePath);
                    rentalsRepository = new RentalsRepository();
                    initVehicleRepo();
                    initCustomersAndRentals();
                    break;
                case "database":
                    vehicleRepository = new VehicleRepositoryDatabase(filePath);
                    rentalsRepository = new RentalsRepository();
                    initCustomersAndRentals();
                    break;
                default:
                    System.out.println("Invalid vehicleRepoType: " + vehicleRepoType);
                    break;
            }
            } catch (Exception e) {
                e.printStackTrace();
        }

    }

    public void initVehicleRepo() throws DuplicateEntityException {
        vehicleRepository.add(new Vehicle(1, "Golf 4", "Berlina", "Red", 2005, 5800, 190700, true));
        vehicleRepository.add(new Vehicle(2, "Logan", "SUV", "White", 2011, 10000, 30330, true));
        vehicleRepository.add(new Vehicle(3, "Audi A5", "SUV", "Black", 2016, 140000, 122000, false));
        vehicleRepository.add(new Vehicle(4, "Opel Astra", "Hatchback", "Silver", 2012, 33400, 63340, false));
        vehicleRepository.add(new Vehicle(5, "Seat Ibiza", "Hatchback", "Yellow", 2019, 16550, 64456, false));
        vehicleRepository.add(new Vehicle(6, "BMW x6", "SUV", "Black", 2020, 350000, 12000, true));
        vehicleRepository.add(new Vehicle(7, "Opel Corsa", "SUV", "Carbon Red", 2035, 80000, 93000, false));
        vehicleRepository.add(new Vehicle(8, "Ford Raptor", "Truck", "Laguna Blue", 2040, 10000, 12200, false));
    }

    public void initCustomersAndRentals() throws DuplicateEntityException {
        Customer customer = new Customer(1, "Sabin Mihai", "Strada Magheru", "07401232131", "email1@gmail.com");
        Customer customer2 = new Customer(2, "Pista Matei", "Aleea Ciocarliei", "07401244444", "email2@yahoo.com");
        Customer customer3 = new Customer(3, "Horea Turc", "Aleea Barsei 3", "0740122222", "email3@gmail.com");
        rentalsRepository.add(new CarRental(1, 1, 1, 5, 17300, 1555));
        rentalsRepository.add(new CarRental(2, 2, 2, 3, 24000, 1440));
        rentalsRepository.add(new CarRental(3, 6, 3, 7, 2500, 57320));
    }

    public Service() {
        initService();
    }
    
    public void addCarRental(CarRental carRental) throws DuplicateEntityException {
        rentalsRepository.add(carRental);
        vehicleRepository.findById(carRental.getIdVehicle()).setIsRented(true);
    }

    public void deleteVehicle(Integer id) throws NonExistingEntityException {
        vehicleRepository.delete(id);
    }

    public void addVehicle(Vehicle vehicle) throws DuplicateEntityException {
        vehicleRepository.add(vehicle);
    }

    public Vehicle findCarById(Integer id) {
        return vehicleRepository.findById(id);
    }

    public CarRental findRentalById(Integer id) {
        return rentalsRepository.findById(id);
    }


    public void deleteCarRental(Integer id) throws NonExistingEntityException {
        vehicleRepository.findById(rentalsRepository.findById(id).getIdVehicle()).setIsRented(false);
        rentalsRepository.delete(id);
    }

    public void updateVehicle(Vehicle vehicle) {
        vehicleRepository.update(vehicle);
    }

    public void updateCarRental(CarRental carRental) {
        rentalsRepository.update(carRental);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.getAll();
    }

    public List<CarRental> getAllCarRentals() {
        return rentalsRepository.getAll();
    }

    public List<Vehicle> getAllRentedVehicles() {
        List<Vehicle> result = vehicleRepository.getAll().stream().filter(v -> v.getIsRented()).toList();
        for (Vehicle v : result) {
            System.out.println(v.toString());
        }
        return result;
    }

    public List<Vehicle> getAllAvailableVehicles() {
        List<Vehicle> result = vehicleRepository.getAll().stream().filter(v -> !v.getIsRented()).toList();
        for (Vehicle v : result) {
            System.out.println(v.toString());
        }
        return result;
    }

    public List<Vehicle> getAllVehiclesWithPriceLessThan(Integer price) {
        List<Vehicle> result = vehicleRepository.getAll().stream().filter(v -> v.getPrice() < price).toList();
        for (Vehicle v : result) {
            System.out.println(v.toString());
        }
        return result;
    }

    public List<Vehicle> getAllVehiclesWithMileageLowerThan(Integer mileage) {
        List<Vehicle> result = vehicleRepository.getAll().stream().filter(v -> v.getMileage() < mileage).toList();
        for (Vehicle v : result) {
            System.out.println(v.toString());
        }
        return result;
    }

    public List<Vehicle> getAllVehiclesRentedByType(String type) {
        List<Vehicle> result = vehicleRepository.getAll().stream().filter(v -> v.getType().equals(type) && v.getIsRented()).toList();
        for (Vehicle v : result) {
            System.out.println(v.toString());
        }
        return result;
    }
}
