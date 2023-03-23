package com.employee.app.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EmployeeDtoWrapper {

    @Valid
    @NotEmpty(message = "employees can not be empty")
    private List<EmployeeDTO> bulks;

    @JsonCreator
    public EmployeeDtoWrapper(List<EmployeeDTO> bulks) {
        this.bulks = bulks;
    }

    public EmployeeDtoWrapper() {
    }

    @JsonValue
    public List<EmployeeDTO> getBulks() {
        return bulks;
    }

    public void setBulks(List<EmployeeDTO> bulks) {
        this.bulks = bulks;
    }

    public static void main(String[] args) throws IOException {
        EmployeeDTO b1 = new EmployeeDTO();
        b1.setEmail("ankit@gmail.com");
        EmployeeDTO b2 = new EmployeeDTO();
        b2.setEmail("ajit@gmail.com");

        EmployeeDtoWrapper wrapper = new EmployeeDtoWrapper();
        wrapper.setBulks(Arrays.asList(b1, b2));

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(wrapper);
        System.out.println("json = " + json);

        EmployeeDtoWrapper wrapper2 = om.readValue(json, EmployeeDtoWrapper.class);
        System.out.println("Size:: "+wrapper2.getBulks().size());
        json = om.writeValueAsString(wrapper2);
        System.out.println("output json = " + json);
    }
}
