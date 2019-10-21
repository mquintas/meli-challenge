package meli.challenge.model;

import com.google.common.base.Joiner;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.log4j.Log4j2;
import meli.challenge.service.PronosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class SistemaSolar {

    @Autowired
    private PronosticoService service;

    private Planeta sol = new Planeta("SOL",0, 0, 0);
    private Planeta ferengi;
    private Planeta betasoide;
    private Planeta vulcano;

    private double diasDePronostico = 10*365; //cuando dice 10 años se presupone años terrestres de 365 dias sin bisiesto

    private int planetasAlineados = 0;
    private int planetasAlineadosConElSol = 0;
    private List<Integer> diasSequia = new ArrayList<>();
    private List<Integer> diasOptimos = new ArrayList<>();


    private int planetasTrianguladosConElSol = 0;
    private List<Integer> diasLluvia = new ArrayList<>();
    private List<Integer> diasPicoDeLluvia = new ArrayList<>();
    private double perimetroMaximo = 0;

    Multimap<Double, Integer> distribucionDeLluvias = ArrayListMultimap.create();

    List<Pronostico> pronosticoList = new ArrayList<>();

    public void pronosticar() {

        initPlanetas();
        for (int dia = 0 ; dia < diasDePronostico; dia++){
            Pronostico pronostico = new Pronostico(dia);
            //print(dia);
            posicionarPlanetas(pronostico);
            calcularAlineaciones(pronostico);
            calcularTriangulaciones(pronostico);
            pronosticoList.add(pronostico);
            service.agregarPronostico(pronostico);
        }

        //seteo en cada pronostico si es pico de lluvia dependiendo el maximo perimetro calculado.
        distribucionDeLluvias.get(perimetroMaximo).forEach( (dia) -> {
            service.obtenerPronostico(dia).setPicoDeLluvia("SI");
        });

        log.info(" ========== Resumen del Pronostico ========== ");
        log.info("Periodos de Sequia: "+ planetasAlineadosConElSol);
        log.info("Dias De Sequia: "+ Joiner.on(",").join(diasSequia));
        log.info("Periodos de clima Optimo: "+ planetasAlineados);
        log.info("Dias De optimos: "+ Joiner.on(",").join(diasOptimos));
        log.info("Periodos de Lluvia: "+ planetasTrianguladosConElSol);
        log.info("Dias De lluvia: "+ Joiner.on(",").join(diasLluvia));
        log.info("Dias con Pico De lluvia: "+ Joiner.on(",").join(distribucionDeLluvias.get(perimetroMaximo)));
        log.info(" =========================================== ");

    }

    private void calcularTriangulaciones(Pronostico pronostico) {
        //http://www.dma.fi.upm.es/personal/mabellanas/tfcs/kirkpatrick/Aplicacion/algoritmos.htm
        //calculo la orientacion del triangulo entre los 3 planetas y luego entre 3 planetas y el sol
        //FVB - ferengi, vulcano, betasoide
        double fvb = (ferengi.x()-betasoide.x())*(vulcano.y()-betasoide.y()) - (ferengi.y()-betasoide.y()) * (vulcano.x()-betasoide.x());
        //las coordenadas del sol siempre van a ser 0 pero las dejo para ejemplificar la cuenta.
        //fvs
        double fbs = (ferengi.x()-sol.x())*(vulcano.y()-sol.y()) - (ferengi.y()-sol.y()) * (vulcano.x()-sol.x());
        //vbs
        double vbs= (vulcano.x()-sol.x())*(betasoide.y()-sol.y()) - (vulcano.y()-sol.y()) * (betasoide.x()-sol.x());
        //bfs
        double bfs= (betasoide.x()-sol.x())*(ferengi.y()-sol.y()) - (betasoide.y()-sol.y()) * (ferengi.x()-sol.x());

        if ( !(fvb == 0 && fbs == 0 && fbs == 0 && vbs == 0 ) &&
              ( Math.signum(fvb) == Math.signum(fbs) && Math.signum(fbs) == Math.signum(vbs) && Math.signum(vbs) == Math.signum(bfs)) ) {
            //los sentidos de los 4 triangulos son el mismo, por lo tanto contiene al Sol.
            planetasTrianguladosConElSol++;
            diasLluvia.add(pronostico.getDia());
            pronostico.setClima(Clima.LLUVIA);

            //calculo perimetro con los 3 vectores que forman el triangulo
            double fv = Math.sqrt(Math.pow((vulcano.x() - ferengi.x()), 2) + Math.pow((vulcano.y() - ferengi.y()), 2));
            double vb = Math.sqrt(Math.pow((betasoide.x() - vulcano.x()), 2) + Math.pow((betasoide.y() - vulcano.y()), 2));
            double bf = Math.sqrt(Math.pow((ferengi.x() - betasoide.x()), 2) + Math.pow((ferengi.y() - betasoide.y()), 2));

            double perimetro = fv + vb + bf;
            distribucionDeLluvias.put(perimetro, pronostico.getDia());

            if (perimetro >= perimetroMaximo) {
                perimetroMaximo = perimetro;
                diasPicoDeLluvia.add(pronostico.getDia());
            }
        }
    }

    /**
     * metodo de la linea Y = m X + b
     */
    private void calcularAlineaciones(Pronostico pronostico) {
        double m = 0;
        if ( (vulcano.x() - betasoide.x()) != 0) {
            m = (vulcano.y() - betasoide.y()) / (vulcano.x() - betasoide.x());
            //double b = ( (betasoide.y()*vulcano.x()) - (vulcano.y()-betasoide.x())) / (vulcano.x() - betasoide.x());
        }
        double b = vulcano.y() - (m * vulcano.x());

        //contiene a ferengi?
        double y = ( m*ferengi.x() )+ b;

        if (m == 0 && b == 0 && ferengi.y() == 0 ) {
            //es una recta horizontal y los 3 planetas pasan por ahi
            planetasAlineadosConElSol++;
            diasSequia.add(pronostico.getDia());
            pronostico.setClima(Clima.SEQUIA);
        } else if (m == 0 && ferengi.x() == 0) {
            //es una recta vertical y los 3 planetas pasan por ahi
            planetasAlineadosConElSol++;
            diasSequia.add(pronostico.getDia());
            pronostico.setClima(Clima.SEQUIA);
        } else if (Math.abs(y - ferengi.y()) < 0.001) { // comparo < 0.001 por si tengo algun error de redondeo aunque el resultado no cambio.
            //los 3 planetas estan alineados. verifico el sol. si m=0 la recta pasa por (0,0)
            planetasAlineados++;
            diasOptimos.add(pronostico.getDia());
            pronostico.setClima(Clima.OPTIMO);
        }

    }


//    /**
//     * Metodo de vectores.... no funciono.
//     * @deprecated no me daban los numeros.
//     */
//    private void estanAlineados(int dia) {
//        //Ferengi (F), Betasoide (B) y Vulcano (V) estan alineados si
//        //--- Vx-Fx / Bx-Fx = Vy-Fy / By-Fy
//        /**
//         *   By-Fy   Vy-By
//         *   ----- = -----
//         *   Bx-Fx   Vx-Bx
//         */
//        if (vulcano.x() == ferengi.x() && ferengi.x() == betasoide.x()) {
////            planetasAlineados++;
//            return;
//        }
//        if (vulcano.y() == ferengi.y() && ferengi.y() == betasoide.y()) {
////            planetasAlineados++;
//            return;
//        }
////        double a = (new BigDecimal( (betasoide.y() - ferengi.y()) / (betasoide.x() - ferengi.x()) )).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
////        double b = (new BigDecimal( (vulcano.y() - betasoide.y()) / (vulcano.x() - betasoide.x()) )).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
//        double a = ( (betasoide.y() - ferengi.y()) / (betasoide.x() - ferengi.x()) );
//        double b = ( (vulcano.y() - betasoide.y()) / (vulcano.x() - betasoide.x()) );
//
//        log.info( Math.abs(a - b));
//        if ( Math.abs(a - b)  < 0.0001 ) {
//            planetasAlineados = planetasAlineados + 1;
//            diasOptimos.add(dia);
//        }
//    }

    private void posicionarPlanetas(Pronostico pronostico) {
        ferengi.posicionar(pronostico.getDia());
        betasoide.posicionar(pronostico.getDia());
        vulcano.posicionar(pronostico.getDia());
    }

    private void initPlanetas() {
        ferengi = new Planeta("FERENGI",1, -1, 500);
        betasoide = new Planeta("BETASOIDE",3, -1, 2000);
        vulcano = new Planeta("VULCANO",5, +1, 1000);
    }

    private void print(int dia) {
        log.info("DIA: " + dia);
        log.info(ferengi);
        log.info(betasoide);
        log.info(vulcano);
        log.info("----");

    }

}