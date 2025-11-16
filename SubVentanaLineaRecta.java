import java.awt.Frame;

public class SubVentanaLineaRecta extends SubVentanaBase {

    public SubVentanaLineaRecta(Frame owner, double costo, double rescate, int vidaUtil) {
        super(owner, "Método Línea Recta");
        calcular(costo, rescate, vidaUtil);
    }

    private void calcular(double costo, double rescate, int vidaUtil) {
        double valorActual = costo;
        double depreciacionAnual = (costo - rescate) / vidaUtil;

        for (int anio = 1; anio <= vidaUtil; anio++) {
            double valorFinal = valorActual - depreciacionAnual;
            
            // Usamos el método del padre para agregar a la tabla
            agregarFila(anio, valorActual, depreciacionAnual, valorFinal);
            
            valorActual = valorFinal;
        }
    }
}