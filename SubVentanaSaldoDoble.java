import java.awt.Frame;

public class SubVentanaSaldoDoble extends SubVentanaBase {

    public SubVentanaSaldoDoble(Frame owner, double costo, double rescate, int vidaUtil) {
        super(owner, "Método Saldo Doble Decreciente");
        calcular(costo, rescate, vidaUtil);
    }

    private void calcular(double costo, double rescate, int vidaUtil) {
        double valorActual = costo;
        
        // FÓRMULA DEL DOCUMENTO[cite: 33]: d = 2 / n
        double tasa = 2.0 / vidaUtil; 

        for (int anio = 1; anio <= vidaUtil; anio++) {
            
            // Dt = Bt * tasa
            double depreciacion = valorActual * tasa;

            // REGLA DE AJUSTE[cite: 22, 34]:
            // El valor en libros nunca puede bajar del valor de rescate.
            // Si al restar la depreciación calculada bajamos del rescate, 
            // AJUSTAMOS la depreciación.
            
            if (valorActual - depreciacion < rescate) {
                depreciacion = valorActual - rescate; // El "tapón" final
            }

            double valorFinal = valorActual - depreciacion;
            
            agregarFila(anio, valorActual, depreciacion, valorFinal);
            
            valorActual = valorFinal;
            
            // Si ya llegamos al valor de rescate, detenemos el ciclo aunque falten años
            // (Por si la tasa es muy alta y se deprecia antes de tiempo)
            if (Math.abs(valorActual - rescate) < 0.01) break;
        }
    }
}