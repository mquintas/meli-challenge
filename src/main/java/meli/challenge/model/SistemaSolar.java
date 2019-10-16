package meli.challenge.model;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class SistemaSolar {


    private Planeta sol = new Planeta("SOL",0, 0, 0);
    private Planeta ferengi;
    private Planeta betasoide;
    private Planeta vulcano;

    private double diasDesdeInicio = 0;
    private double diezAnios = 10*365; //cuando dice 10 años se presupone años terrestres de 365 dias sin bisiesto

    private int periodosOptimos = 0;

    public int calcular() {

        initPlanetas();


        for (int dia =0 ; dia < diezAnios; dia++){

            posicionarPlanetas(dia);
            print(dia);
            estanAlineados();
        }
        System.out.println("Periodos De Sequia: "+ periodosOptimos);
        return periodosOptimos;

    }

    private void estanAlineados() {

        //Ferengi (F), Betasoide (B) y Vulcano (V) estan alineados si
        // Vx-Fx / Bx-Fx = Vy-Fy / By-Fy

        if (vulcano.x() == ferengi.x() && ferengi.x() == betasoide.x()) {
            periodosOptimos++;
            return;
        }
        if (vulcano.y() == ferengi.y() && ferengi.y() == betasoide.y()) {
            periodosOptimos++;
            return;
        }
        double x = (new BigDecimal( (vulcano.x() - ferengi.x()) / (betasoide.x() - ferengi.x()) )).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        double y = (new BigDecimal( (vulcano.y() - ferengi.y()) / (betasoide.y() - ferengi.y()) )).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

        periodosOptimos = periodosOptimos + ( (x == y) ? 1 : 0 );
    }

    private void posicionarPlanetas(int dia) {
        ferengi.posicionar(dia);
        betasoide.posicionar(dia);
        vulcano.posicionar(dia);
    }

    private void initPlanetas() {
        ferengi = new Planeta("FERENGI",1, -1, 500);
        betasoide = new Planeta("BETASOIDE",3, -1, 2000);
        vulcano = new Planeta("VULCANO",5, +1, 1000);
    }

    private void print(int dia) {
        System.out.println("DIA: " + dia);
        System.out.println(ferengi);
        System.out.println(betasoide);
        System.out.println(vulcano);
        System.out.println("----");

    }

}