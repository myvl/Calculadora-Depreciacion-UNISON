import java.awt.Frame;
import javax.swing.JOptionPane;

public class SubVentanaSMARC extends SubVentanaBase {

    public SubVentanaSMARC(Frame owner, double costo, double rescate, int vidaUtil) {
        super(owner, "Método SMARC (MACRS)");
        calcular(costo, rescate, vidaUtil);
    }

    private void calcular(double costo, double rescate, int vidaUtil) {
        double valorActual = costo;
        
        // Tablas oficiales de porcentajes anuales de SMARC (MACRS)
        double[] tasas;

        switch (vidaUtil) {
            case 3:
                tasas = new double[]{0.3333, 0.4445, 0.1481, 0.0741};
                break;
            case 5:
                tasas = new double[]{0.2000, 0.3200, 0.1920, 0.1152, 0.1152, 0.0576};
                break;
            case 7:
                tasas = new double[]{0.1429, 0.2449, 0.1749, 0.1249, 0.0893, 0.0892, 0.0893, 0.0446};
                break;
            case 10:
                tasas = new double[]{0.1000, 0.1800, 0.1440, 0.1152, 0.0922, 0.0737, 0.0655, 0.0655, 0.0656, 0.0655, 0.0328};
                break;
            default:
                // Si escogen años raros (ej: 4, 6, 8), usamos la tabla de 5 años por defecto 
                // o mostramos error. Aquí usaré la de 5 años como fallback para que no falle.
                JOptionPane.showMessageDialog(this, "Nota: Para SMARC se usarán las tasas estándar de 5 años.");
                tasas = new double[]{0.2000, 0.3200, 0.1920, 0.1152, 0.1152, 0.0576};
                break;
        }

        // NOTA IMPORTANTE DE SMARC:
        // 1. Se aplica el % siempre al Costo Inicial (no al saldo restante).
        // 2. Ignora el valor de rescate para el cálculo, PERO el valor en libros no debe bajar de cero.
        // 3. Dura un año más que la vida útil (año n+1) por la regla de medio año.

        for (int i = 0; i < tasas.length; i++) {
            int anio = i + 1;
            double tasa = tasas[i];
            
            // La depreciación es: Costo Original * Porcentaje de la tabla
            double depreciacion = costo * tasa;
            
            double valorFinal = valorActual - depreciacion;

            // Ajuste visual: Si el valor final es menor que 0 (o rescate), ajustamos según reglas contables.
            // En SMARC puro, se deprecia todo. Si quieres respetar el rescate manual:
            if (valorFinal < rescate) {
               // Opcional: Descomenta esto si quieres detenerte en el valor de rescate exacto
               // depreciacion = valorActual - rescate;
               // valorFinal = rescate;
            }

            agregarFila(anio, valorActual, depreciacion, valorFinal);
            
            valorActual = valorFinal;
            
            // Si ya llegamos a 0 o menos, terminamos
            if (valorActual <= 0.01) break;
        }
    }
}