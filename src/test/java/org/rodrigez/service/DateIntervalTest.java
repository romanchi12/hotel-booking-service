package org.rodrigez.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rodrigez.service.exceptions.DateIntervalException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateIntervalTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void overlaps() {
    }

    @Test(expected = DateIntervalException.class)
    public void from_after_until() throws Exception {
        new DateInterval("2018-12-10","2018-11-11");
    }
}