import javax.swing.*;
import javax.swing.event.AncestorListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {

    // Colores y Fuentes
    private final Color COLOR_FONDO = new Color(237, 233, 254);
    private final Color COLOR_TEXTO = new Color(30, 58, 138);
    private final Color COLOR_BLANCO = Color.WHITE;
    private final Font FONT_LABEL = new Font("SansSerif", Font.BOLD, 16);

    private JComboBox<String> cbTipo;
    private JTextField txtCosto, txtRescate;
    private JComboBox<Integer> cbVidaUtil;

    public void showVentana() {
        setTitle("Calculadora de Depreciación - UNISON");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(COLOR_FONDO);
        setContentPane(mainPanel);

        // --- Header y Decoración (Simplificado para el ejemplo) ---
        ImageIcon iconoOriginal = new ImageIcon("logo.png");
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon iconoFinal = new ImageIcon(imagenEscalada);
        JLabel lblLogo = new JLabel(iconoFinal);
        lblLogo.setBounds(280, 23, 80, 80); // Misma posición que antes
        mainPanel.add(lblLogo);

        JLabel titleUni = new JLabel("UNIVERSIDAD DE SONORA");
        titleUni.setFont(new Font("Serif", Font.PLAIN, 28));
        titleUni.setForeground(COLOR_TEXTO);
        titleUni.setBounds(370, 40, 500, 30);
        mainPanel.add(titleUni);

        JLabel titleUni2 = new JLabel("El saber de mis hijos hará mi grandeza");
        titleUni2.setFont(new Font("Serif", Font.ITALIC, 18));
        titleUni2.setForeground(COLOR_TEXTO);
        titleUni2.setBounds(385, 75, 500, 20);
        mainPanel.add(titleUni2);

        JPanel headerLine = new JPanel();
        headerLine.setBounds(0, 15, 1000, 95);
        headerLine.setBackground(COLOR_BLANCO);
        mainPanel.add(headerLine);

        JPanel headerLine1 = new JPanel();
        headerLine1.setBounds(0, 10, 1000, 5);
        headerLine1.setBackground(COLOR_TEXTO);
        mainPanel.add(headerLine1);

        JPanel headerLine2 = new JPanel();
        headerLine2.setBounds(0, 110, 1000, 5);
        headerLine2.setBackground(COLOR_TEXTO);
        mainPanel.add(headerLine2);

        

        // --- Formulario ---
        JLabel lblTipo = crearLabel("Tipo:", 60, 240);
        mainPanel.add(lblTipo);
        JLabel lblintro = new JLabel("Calculadora de depreciación");
        lblintro.setFont(new Font("Serif", Font.PLAIN, 28));
        lblintro.setForeground(COLOR_TEXTO);
        lblintro.setBounds(60, 150, 500, 28);
        mainPanel.add(lblintro);

        JLabel lblintru1 = new JLabel("Seleccione el tipo de depreciación:");
        lblintru1.setFont(new Font("Serif", Font.ITALIC, 15));
        lblintru1.setForeground(Color.LIGHT_GRAY);
        lblintru1.setBounds(60, 200, 500, 28);
        mainPanel.add(lblintru1);

         JLabel lblintru2 = new JLabel("Rellene los campos correspondientes:");
        lblintru2.setFont(new Font("Serif", Font.ITALIC, 15));
        lblintru2.setForeground(Color.LIGHT_GRAY);
        lblintru2.setBounds(60, 335, 500, 28);
        mainPanel.add(lblintru2);

        String[] tipos = {"Seleccionar","Línea Recta", "Saldo Decreciente","Saldo Doble Decreciente", "SMARC"};
        cbTipo = new JComboBox<>(tipos);
        cbTipo.setBounds(120, 240, 250, 35);
        cbTipo.setBackground(COLOR_BLANCO);
        mainPanel.add(cbTipo);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(390, 240, 120, 35);
        btnCalcular.setBackground(COLOR_TEXTO);
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setEnabled(false);
        mainPanel.add(btnCalcular);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(370, 550, 100, 35);
        btnLimpiar.setBackground(Color.LIGHT_GRAY);
        btnLimpiar.setForeground(COLOR_TEXTO);
        btnLimpiar.setFocusPainted(false);
        mainPanel.add(btnLimpiar);

        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e ){
                txtCosto.setText(" ");
                txtRescate.setText(" ");
                cbVidaUtil.setSelectedIndex(-1);
                cbTipo.setSelectedIndex(0);
            }
        });
        cbTipo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String seleccionado = (String) cbTipo.getSelectedItem();
                if (seleccionado.equals("Seleccionar")){
                    btnCalcular.setEnabled(false);
                } else {
                    btnCalcular.setEnabled(true);
                }
            }
        });

        JLabel lblCosto = crearLabel("Costo inicial:", 60, 370);
        mainPanel.add(lblCosto);
        txtCosto = new JTextField();
        txtCosto.setBounds(220, 370, 250, 35);
        mainPanel.add(txtCosto);

        JLabel lblRescate = crearLabel("Valor de rescate:", 60, 430);
        mainPanel.add(lblRescate);
        txtRescate = new JTextField();
        txtRescate.setBounds(220, 430, 250, 35);
        mainPanel.add(txtRescate);

        JLabel lblVida = crearLabel("Vida útil:", 60, 490);
        mainPanel.add(lblVida);
        Integer[] anios = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        cbVidaUtil = new JComboBox<>(anios);
        cbVidaUtil.setBounds(220, 490, 250, 35);
        cbVidaUtil.setSelectedItem(-1);
        mainPanel.add(cbVidaUtil);

        // --- Acción del Botón ---
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirSubVentana();
            }
        });
    }

    private JLabel crearLabel(String texto, int x, int y) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(COLOR_TEXTO);
        lbl.setBounds(x, y, 200, 30);
        return lbl;
    }

    private void abrirSubVentana() {
        try {

            if (cbVidaUtil.getSelectedIndex()== -1){
                JOptionPane.showMessageDialog(this, "Seleccione vida útil");
                return;
            }
            double costo = Double.parseDouble(txtCosto.getText());
            double rescate = Double.parseDouble(txtRescate.getText());
            int vida = (int) cbVidaUtil.getSelectedItem();
            String tipo = (String) cbTipo.getSelectedItem();

            if (rescate >= costo) {
                JOptionPane.showMessageDialog(this, "El rescate no puede ser mayor al costo.");
                return;
            }

            // AQUÍ ESTÁ LA MAGIA: Selección de clase
            if (tipo.equals("Línea Recta")) {
                new SubVentanaLineaRecta(this, costo, rescate, vida).setVisible(true);
            } else if (tipo.equals("Saldo Decreciente")) {
                new SubVentanaSaldoDecreciente(this, costo, rescate, vida).setVisible(true);
            } else if (tipo.equals("Saldo Doble Decreciente")) {
                new SubVentanaSaldoDoble(this, costo, rescate, vida).setVisible(true);
            } else if (tipo.equals("SMARC")) {
                new SubVentanaSMARC(this, costo, rescate, vida).setVisible(true);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Revise los números ingresados.");
        }
    }
}
