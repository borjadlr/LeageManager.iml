package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordGUI extends JFrame implements ActionListener {

    private JLabel actualPassword;
    private JLabel newPassword;
    private JLabel repeatedPassword;
    private JTextField txtContraseñaActual;
    private JTextField txtNuevaContraseña;
    private JTextField txtRepetirContraseña;
    private JButton btnCambiar;
    private JButton btnCancelar;

    public ChangePasswordGUI() {

        // Crear componentes
        actualPassword = new JLabel("Contraseña Actual:");
        newPassword = new JLabel("Nueva Contraseña:");
        repeatedPassword = new JLabel("Repetir Nueva Contraseña:");

        txtContraseñaActual = new JPasswordField(20);
        txtNuevaContraseña = new JPasswordField(20);
        txtRepetirContraseña = new JPasswordField(20);

        btnCambiar = new JButton("Change Password");
        btnCancelar = new JButton("Back");

        // Añadir listeners
        btnCambiar.addActionListener(this);
        btnCancelar.addActionListener(this);

        // Crear paneles
        JPanel pnlContraseñaActual = new JPanel(new FlowLayout());
        pnlContraseñaActual.add(actualPassword);
        pnlContraseñaActual.add(txtContraseñaActual);

        JPanel pnlNuevaContraseña = new JPanel(new FlowLayout());
        pnlNuevaContraseña.add(newPassword);
        pnlNuevaContraseña.add(txtNuevaContraseña);

        JPanel pnlRepetirContraseña = new JPanel(new FlowLayout());
        pnlRepetirContraseña.add(repeatedPassword);
        pnlRepetirContraseña.add(txtRepetirContraseña);

        JPanel pnlBotones = new JPanel(new FlowLayout());
        pnlBotones.add(btnCambiar);
        pnlBotones.add(btnCancelar);

        // Añadir paneles al JFrame
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.add(pnlContraseñaActual);
        contentPane.add(pnlNuevaContraseña);
        contentPane.add(pnlRepetirContraseña);
        contentPane.add(pnlBotones);

        // Ajustar tamaño y hacer visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCambiar) {
            // Obtener valores de los campos de texto
            String contraseñaActual = txtContraseñaActual.getText();
            String nuevaContraseña = txtNuevaContraseña.getText();
            String repetirContraseña = txtRepetirContraseña.getText();

            // Comprobar si las contraseñas coinciden
            if (nuevaContraseña.equals(repetirContraseña)) {
                // Aquí se podría implementar la lógica para cambiar la contraseña en la base de datos o en el sistema
                JOptionPane.showMessageDialog(this, "Contraseña cambiada correctamente.");

                // Limpiar campos de texto
                txtContraseñaActual.setText("");
                txtNuevaContraseña.setText("");
                txtRepetirContraseña.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
            }
        } else if (e.getSource() == btnCancelar) {
            dispose(); // Cerrar ventana
        }
    }
}

