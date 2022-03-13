package ru.redramka.zener.dev.model;

public class ProductData {
private String manufacturer;
private String name;


    public ProductData with_manufacturer(String manufacturer){
        this.manufacturer = manufacturer;
        return this;
    }

    public ProductData with_name(String name) {
        this.name = name;
        return this;
    }


    @Override
    public String toString() {
        return "ProductData{" +
                "manufacturer='" + manufacturer + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String get_manufacturer() {
        return manufacturer;
    }

    public String get_name() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductData that = (ProductData) o;

        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = manufacturer != null ? manufacturer.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
