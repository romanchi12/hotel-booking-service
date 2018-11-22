package org.rodrigez.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rodrigez.model.domain.OptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class OptionTypeRepositoryTest {

    @Autowired
    private OptionTypeRepository optionTypeRepository;

    @Test
    public void findById() {
        OptionType optionType = new OptionType();
        optionType.setId(10);
        optionType.setDescription("Test description");
        optionTypeRepository.save(optionType);

        Optional<OptionType> found = optionTypeRepository.findById(optionType.getId());

        assertTrue(found.isPresent());
        assertEquals(found.get().getDescription(), optionType.getDescription());
    }
}