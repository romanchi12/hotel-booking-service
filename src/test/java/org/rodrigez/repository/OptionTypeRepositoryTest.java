package org.rodrigez.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rodrigez.model.domain.OptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class OptionTypeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OptionTypeRepository optionTypeRepository;

    @Test
    void findById() {
        OptionType optionType = new OptionType();
        optionType.setId(1);
        optionType.setDescription("Test description");
        entityManager.persist(optionType);

        Optional<OptionType> found = optionTypeRepository.findById(optionType.getId());

        assertTrue(found.isPresent());
        assertEquals(found.get().getDescription(), optionType.getDescription());
    }
}