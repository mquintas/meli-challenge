package meli.challenge.model;

import lombok.Data;

@Data
public class Planeta {

    private String nombre;
    private Posicion posicion;
    private int velocidadEnGrados;
    private int direccion; //+1 en sentido antihorario | -1 en sentido horario
    private int radio;

    private int anguloInicial; //en grados
    private int anguloConElSol; //en grados

    public Planeta(String nombre, int velocidad, int direccion, int radio) {
        this.nombre = nombre;
        this.velocidadEnGrados = velocidad;
        this.direccion = direccion;
        this.radio = radio;
        //asumo que arrancan alineados en vertical como ejemplifica el enunciado
        this.posicion = new Posicion(0, radio, 90);
        this.anguloInicial = 90;
    }

    public void reset() {
        this.posicion.fijarPos(0, 0);
    }


    public void posicionar(int dia) {

//        if (dia == 90 || dia == 180 || dia == 270 || dia == 360)
//            System.out.println();

        anguloConElSol = (anguloInicial + (velocidadEnGrados * dia * direccion));
        this.posicion.fijarPos(anguloConElSol, radio);
    }

    public double x() {
        return this.posicion.getX();
    }
    public double y() {
        return this.posicion.getY();
    }

    @Override
    public String toString() {
        return "Planeta{" +
                "nombre='" + nombre + '\'' +
                ", posicion=" + posicion +
                '}';
    }
}