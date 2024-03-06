/*
package tests;
import domain.Vehicle;
import domain.CarRental;
import domain.Customer;
import repository.*;
import service.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class unitTesting {
    private final Service serv = new Service();


    @Test
    public void testAddVehicle() throws DuplicateEntityException {
        {
            Vehicle v = new Vehicle(9, Logan ", "Sedan", "Black", 2012, 421200, 2500, false);
            serv.addVehicle(v);
            assertEquals(serv.getAllVehicles().spliterator().getExactSizeIfKnown(), 9);
        }
    }

    @Test
    public void testAddCarRental() throws DuplicateEntityException {
        {
            CarRental c = new CarRental(4, 1, 1, 5, 12500, 15455);
            serv.addCarRental(c);
            assertEquals(serv.getAllCarRentals().spliterator().getExactSizeIfKnown(), 4);
        }
    }

    @Test
    public void testDeleteVehicle() throws NonExistingEntityException {
        {
            serv.deleteVehicle(9);
            assertEquals(serv.getAllVehicles().spliterator().getExactSizeIfKnown(), 8);
        }
    }

    @Test
    public void testDeleteCarRental() throws NonExistingEntityException {
        {
            serv.deleteCarRental(4);
            assertEquals(serv.getAllCarRentals().spliterator().getExactSizeIfKnown(), 3);
        }
    }

    @Test
    public void testUpdateVehicle() {
        {
            Vehicle v = new Vehicle(1, "Golf 5", "Berlina", "black", 2012, 12000, 100000, false);
            serv.updateVehicle(v);
            assertEquals(serv.findCarById(1).getName(), "Audi A4");
        }
    }

    @Test
    public void testUpdateCarRental() {
        {
            CarRental c = new CarRental(1, 1, 1, 5, 17300, 1555);
            serv.updateCarRental(c);
            assertEquals(serv.findRentalById(1).getDays(), 5);
        }
    }

    @Test
    public void testGetAllVehicles() {
        {
            assertEquals(serv.getAllVehicles().spliterator().getExactSizeIfKnown(), 8);
        }
    }
    Customer ctest = new Customer(5, "Sabin Barboi", "Japonia", "0740123456", "meow");
    @Test
    public void testCustomerName() {
        {
            assertEquals(ctest.getName(), "Sabin Barboi");
        }
    }
    @Test
    public void testCustomerAddress() {
        {
            assertEquals(ctest.getAddress(), "Japonia");
        }
    }
    @Test
    public void testCustomerPhoneNumber() {
        {
            assertEquals(ctest.getPhoneNumber(), "0743213456");
        }
    }
    @Test
    public void testCustomerEmail() {
        {
            assertEquals(ctest.getEmail(), "mail");
        }
    }
    @Test
    public void testCustomerID() {
        {
            assertEquals(ctest.getId(), 5);
        }
    }
    @Test
    public void testCustomerSetID() {
        {
            ctest.setId(7);
            assertEquals(ctest.getId(), 7);
        }
    }
    @Test
    public void testCustomerPrint() {
        {
            assertEquals(ctest.printCustomer(), "Customer{" +
                    "id=" + 7 +
                    ", name='" + "Sabin Barboi" + '\'' +
                    ", address='" + "Japonia" + '\'' +
                    ", phoneNumber='" + "0740123456" + '\'' +
                    ", email='" + "meow" + '\'' +
                    '}');
        }
    }
    Vehicle vtest = new Vehicle(10, "Volkswagen Golf", "Hatchback", "Yellow", 2016, 18750, 63256, false);
    @Test
    public void testVehicleName() {
        {
            assertEquals(vtest.getName(), "Volkswagen Golf");
        }
    }
    @Test
    public void testVehicleType() {
        {
            assertEquals(vtest.getType(), "Hatchback");
        }
    }
    @Test
    public void testVehicleColor() {
        {
            assertEquals(vtest.getColor(), "Yellow");
        }
    }
    @Test
    public void testVehicleYear() {
        {
            assertEquals(vtest.getYear(), 2016);
        }
    }
    @Test
    public void testVehiclePrice() {
        {
            assertEquals(vtest.getPrice(), 18750);
        }
    }
    @Test
    public void testVehicleMileage() {
        {
            assertEquals(vtest.getMileage(), 60256);
        }
    }
    @Test
    public void testVehicleID() {
        {
            assertEquals(vtest.getId(), 10);
        }
    }
    @Test
    public void testVehicleSetID() {
        {
            vtest.setId(7);
            assertEquals(vtest.getId(), 7);
        }
    }
    @Test
    public void testVehicleSetIsRented() {
        {
            vtest.setIsRented(true);
            assertTrue(vtest.getIsRented());
        }
    }
    @Test
    public void testVehicleSetName() {
        {
            vtest.setName("Audi R8");
            assertEquals(vtest.getName(), "Seria 5");
        }
    }
    @Test
    public void testVehicleSetType() {
        {
            vtest.setType("Sport Car");
            assertEquals(vtest.getType(), "Sport Car");
        }
    }
    @Test
    public void testVehicleSetColor() {
        {
            vtest.setColor("Twilight Purple");
            assertEquals(vtest.getColor(), "Twilight Purple");
        }
    }
    @Test
    public void testVehicleSetYear() {
        {
            vtest.setYear(2024);
            assertEquals(vtest.getYear(), 2024);
        }
    }
    @Test
    public void testVehicleSetPrice() {
        {
            vtest.setPrice(150000);
            assertEquals(vtest.getPrice(), 150000);
        }
    }
    @Test
    public void testVehicleSetMileage() {
        {
            vtest.setMileage(500);
            assertEquals(vtest.getMileage(), 500);
        }
    }


    @Test
    public void testCarRentalCarId() {
        {
            assertEquals(crtest.getIdVehicle(), 10);
        }
    }
    @Test
    public void testCarRentalCustomerId() {
        {
            assertEquals(crtest.getIdCustomer(), 5);
        }
    }
    @Test
    public void testCarRentalDays() {
        {
            assertEquals(crtest.getDays(), 12);
        }
    }
    @Test
    public void testCarRentalKilometers() {
        {
            assertEquals(crtest.getKilometers(), 7540);
        }
    }
    @Test
    public void testCarRentalPrice() {
        {
            assertEquals(crtest.getPrice(), 1449);
        }
    }
    @Test
    public void testCarRentalSetID() {
        {
            crtest.setId(7);
            assertEquals(crtest.getId(), 7);
        }
    }


    public void runAllTests() throws DuplicateEntityException, NonExistingEntityException {
        testAddVehicle();
        testAddCarRental();
        testDeleteVehicle();
        testDeleteCarRental();
        testUpdateVehicle();
        testUpdateCarRental();
        testGetAllVehicles();
        testCustomerName();
        testCustomerAddress();
        testCustomerPhoneNumber();
        testCustomerEmail();
        testCustomerID();
        testCustomerSetID();
        testCustomerPrint();
        testVehicleName();
        testVehicleType();
        testVehicleColor();
        testVehicleYear();
        testVehiclePrice();
        testVehicleMileage();
        testVehicleID();
        testVehicleSetID();
        testVehicleSetIsRented();
        testVehicleSetName();
        testVehicleSetType();
        testVehicleSetColor();
        testVehicleSetYear();
        testVehicleSetPrice();
        testVehicleSetMileage();
        testVehiclePrint();
        testCarRentalCarId();
        testCarRentalCustomerId();
        testCarRentalDays();
        testCarRentalKilometers();
        testCarRentalPrice();
        testCarRentalSetID();
        testCarRentalPrint();
        testTextFileRepository();
        testBinaryFileRepository();
    }

}
*/
