package ink.afro.entity;

import lombok.Data;

@Data
public class Demo {
    private String name;

    public Demo() {
    }

    public Demo(String name) {
        this.name = name;
    }
}
