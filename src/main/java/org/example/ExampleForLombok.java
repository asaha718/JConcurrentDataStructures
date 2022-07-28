package org.example;

//import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Data

public class ExampleForLombok {
    private String name;
    private int age;
    private String specialty;

    public ExampleForLombok() {
        this.getAge();
    }
}