import java.awt.Frame;

public class SubVentanaSumaDigitos extends SubVentanaBase {

    public SubVentanaSumaDigitos(Frame owner, double costo, double rescate, int vidaUtil) {
        super(owner, "Método Suma de Dígitos");
        calcular(costo, rescate, vidaUtil);
    }

    private void calcular(double costo, double rescate, int vidaUtil) {
        double valorActual = costo;
        int sumaDigitos = (vidaUtil * (vidaUtil + 1)) / 2; // Fórmula n(n+1)/2

        for (int anio = 1; anio <= vidaUtil; anio++) {
            int vidaRestante = vidaUtil - anio + 1;
            double depreciacion = (costo - rescate) * ((double)vidaRestante / sumaDigitos);
            
            double valorFinal = valorActual - depreciacion;

            agregarFila(anio, valorActual, depreciacion, valorFinal);

            valorActual = valorFinal;
        }
    }
}