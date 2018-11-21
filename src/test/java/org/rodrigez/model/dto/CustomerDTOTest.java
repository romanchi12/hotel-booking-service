package org.rodrigez.model.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rodrigez.model.domain.Customer;

import java.util.ArrayList;

public class CustomerDTOTest {

    private CustomerDTO dto;
    private Customer expected;

    @Before
    public void setUp() {
        dto = new CustomerDTO();
        dto.setId(1);
        dto.setName("Mykola");
        dto.setBookingList(new ArrayList<>());

        expected = new Customer();
        expected.setId(1);
        expected.setName("Mykola");
        expected.setBookingList(new ArrayList<>());

    }

    @Test
    public void toEntity() {
        Assert.assertEquals(dto.toEntity(), expected);
    }
}