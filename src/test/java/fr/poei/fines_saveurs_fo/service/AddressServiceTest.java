package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.controller.dto.MapStructMapper;
import fr.poei.fines_saveurs_fo.entity.Address;
import fr.poei.fines_saveurs_fo.entity.AddressCustomer;
import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.repository.AddressCustomerRepository;
import fr.poei.fines_saveurs_fo.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService underTest;

    @Mock
    private AddressCustomerRepository addressCustomerRepoMock;
    @Mock
    private AddressRepository addressRepoMock;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ShouldReturnAnAddressGivenCustomer() {
        Customer customer = new Customer();

        AddressCustomer addressCustomer = new AddressCustomer();
        Address expectedAddress = new Address();
        addressCustomer.setAddress(expectedAddress);

        Mockito.when(addressCustomerRepoMock.findByCustomerAndType(Mockito.any(Customer.class), Mockito.eq("destination")))
                .thenReturn(Optional.of(addressCustomer));

        Address result = underTest.getDestinationAddress(customer);

        assertEquals(expectedAddress, result);
    } // getDestinationAddress & getInvoicingAddress

    @Test
    public void ShouldUpdateAnAddress() {
        Address addressToUpdate = new Address();
        addressToUpdate.setId(1);
        addressToUpdate.setAdditionalInformation("additional information");
        addressToUpdate.setCity("city");
        addressToUpdate.setPostcode("postcode");
        addressToUpdate.setStreet("street");
        addressToUpdate.setStreetNumber("street number");

        underTest.updateAddress(addressToUpdate);

        Mockito.verify(addressRepoMock).updateAddress(
                addressToUpdate.getAdditionalInformation(),
                addressToUpdate.getCity(),
                addressToUpdate.getPostcode(),
                addressToUpdate.getStreet(),
                addressToUpdate.getStreetNumber(),
                addressToUpdate.getId()
        );
    } //

    @Test
    public void ShouldSaveCustomerAddress() {
        Address address = new Address();
        Customer customer = new Customer();
        String type = "destination";

        underTest.saveCustomerAddress(address, customer, type);

        Mockito.verify(addressRepoMock).save(address);

        AddressCustomer expectedAddressCustomer = new AddressCustomer();
        expectedAddressCustomer.setAddress(address);
        expectedAddressCustomer.setCustomer(customer);
        expectedAddressCustomer.setType(type);
        Mockito.verify(addressCustomerRepoMock).save(expectedAddressCustomer);
    } // saveCustomerAddress

}
