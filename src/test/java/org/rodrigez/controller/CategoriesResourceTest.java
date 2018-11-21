package org.rodrigez.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rodrigez.service.InventoryService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CategoriesResource.class, secure = false)
class CategoriesResourceTest {

    //@Autowired
    //private MockMvc mvc;

    @MockBean
    InventoryService inventoryService;

    @Test
    void testGetCategories() {
        //nventoryService service = new InventoryServiceImpl();
        //CategoriesResource resource = new CategoriesResource();
        //assertNotNull(inventoryService);
        //assertNotNull(resource);
        //System.out.println(resource.modelMapper);
        //System.out.println(resource.inventoryService);
        //ApiResponse response = resource.getCategories();
        //assertEquals(Status.ERROR, response.getStatus());
    }
}