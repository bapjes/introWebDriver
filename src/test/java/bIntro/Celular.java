package bIntro;

public class Celular {
    private String nombre;
    private float precio;
    private String capacidad;

    public String getName()
    {
        return this.nombre;
    }


    public void setName(String name)
    {
        this.nombre = name;
    }

    public  void setPrecio(float price)
    {
        this.precio = price;
    }

    public float getPrecio()
    {
        return this.precio;
    }

    public String getCapacidad()
    {
        return this.capacidad;
    }

    public void setCapacidad(String capacity)
    {
        this.capacidad = capacity;
    }
}
