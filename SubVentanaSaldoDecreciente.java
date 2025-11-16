import java.awt.Frame;

public class SubVentanaSaldoDecreciente extends SubVentanaBase {

    public SubVentanaSaldoDecreciente(Frame owner, double costo, double rescate, int vidaUtil) {
        super(owner, "Método Saldo Decreciente"); // Título basado en doc
        calcular(costo, rescate, vidaUtil);
    }

    private void calcular(double costo, double rescate, int vidaUtil) {
        double valorActual = costo;

        // FÓRMULA DEL DOCUMENTO: d = 1 - (S / B)^(1/n)
        // En Java, la raíz enésima se hace con Math.pow(base, 1.0/n)
        double tasa = 1.0 - Math.pow((rescate / costo), 1.0 / vidaUtil);

        // Esto es solo para mostrar la tasa en consola si quieres verificar (ej: 0.369)
        System.out.println("Tasa calculada SD: " + tasa);

        for (int anio = 1; anio <= vidaUtil; anio++) {
            
            // Dt = Bt * d [cite: 5]
            double depreciacion = valorActual * tasa;
            
            // Calculamos el valor final
            double valorFinal = valorActual - depreciacion;

            // Ajuste de precisión: Si es el último año, a veces los decimales 
            // hacen que no cuadre exacto al centavo, forzamos el cierre al valor de rescate
            // si la diferencia es mínima.
            if (anio == vidaUtil) {
                valorFinal = rescate;
                depreciacion = valorActual - valorFinal;
            }

            agregarFila(anio, valorActual, depreciacion, valorFinal);
            
            valorActual = valorFinal;
        }
    }
}
