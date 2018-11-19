package org.rodrigez.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 3L;

    private long id;
    private String name;
    @JsonManagedReference
    private List<BookingDTO> bookingList;

}
