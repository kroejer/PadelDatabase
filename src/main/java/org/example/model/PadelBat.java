package org.example.model;

public class PadelBat {
    private final Integer id;
    private final String model;
    private final String brand;
    private final String form;

    public PadelBat(Integer id, String model, String brand, String form) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.form = form;
    }

    public PadelBat(String model, String brand, String form) {
        this.id = null;
        this.model = model;
        this.brand = brand;
        this.form = form;
    }

    public Integer getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getForm() {
        return form;
    }

    @Override
    public String toString() {
        return "PadelBat[id=" + id + ", model=" + model + ", brand=" + brand + ", form=" + form + "]";
    }
}
