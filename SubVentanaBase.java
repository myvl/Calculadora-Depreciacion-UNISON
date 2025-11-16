import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;

// Clase PADRE: Define el diseño visual, los hijos definirán la matemática
public class SubVentanaBase extends JDialog {
    
    protected DefaultTableModel modelo;
    protected DecimalFormat df = new DecimalFormat("#,##0.00");

    public SubVentanaBase(Frame owner, String tituloMetodo) {
        super(owner, "Resultados - " + tituloMetodo, true);
        setSize(800, 500);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        JPanel panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBackground(Color.WHITE);
        panelFondo.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Depreciación: " + tituloMetodo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 58, 138));
        panelFondo.add(lblTitulo, BorderLayout.NORTH);

        // Configurar Tabla
        String[] columnas = {"Año", "Valor Inicial", "Depreciación", "Valor Final"};
        modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(30);
        
        // Centrar contenido
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0; i<4; i++) tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        panelFondo.add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelFondo);
    }

    // Método auxiliar para agregar filas fácilmente
    protected void agregarFila(int anio, double valIni, double dep, double valFin) {
        modelo.addRow(new Object[]{
            anio, 
            df.format(valIni), 
            df.format(dep), 
            df.format(valFin)
        });
    }
}