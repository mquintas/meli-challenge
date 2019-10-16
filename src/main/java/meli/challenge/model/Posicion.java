package meli.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
public class Posicion {

    //atributos que definen la posicion del Planeta
    private double x;
    private double y;
    private int angulo;

    public Posicion(double x, double y, int anguloInicial) {
        this.x = x;
        this.y = y;
        this.angulo = anguloInicial;
    }

    public void fijarPos(int angulo, int radio) {

        this.angulo = angulo;
        //x = Xo + r . cos(angulo)
        x = (new BigDecimal(radio * Math.cos(Math.toRadians(angulo)))).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        //y = Yo + r . sen(angulo)
        y = (new BigDecimal(radio * Math.sin(Math.toRadians(angulo)))).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    @Override
    public String toString() {
        return "Posicion{" +
                "x=" + x +
                ", y=" + y +
                ", angulo=" + angulo +
                '}';
    }
}
