import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class login extends JFrame{

    private JTextPane txtPTitulo;
    private JLabel lblUsuarioLogin;
    private JTextField txtUsuario;
    private JPasswordField txtContrasenia;
    private JLabel lblContrasenia;
    private JButton btnEntrar;
    private JButton btnCancelar;
    private JPanel panelLogin;

    public login() {
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // mala practica :c

                String password = String.valueOf(txtContrasenia.getPassword());

                if (txtUsuario.getText().equals("admin") && password.equals("1234")) {
                    //bienvenida
                    JOptionPane.showMessageDialog(panelLogin, "Eres administrador");
                    String[] tipoUsuario = {"admin"};
                    ventanaDoctores.main(tipoUsuario);
                    ventanaCitas.main(tipoUsuario);
                    ventanaPacientes.main(tipoUsuario);

                } else if (txtUsuario.getText().equals("usuario") && password.equals("5678")) {
                    JOptionPane.showMessageDialog(panelLogin, "Eres usuario, sólo puedes consultar");
                    String[] tipoUsuario = {"usuario"};
                    ventanaDoctores.main(tipoUsuario);
                    ventanaCitas.main(tipoUsuario);
                    ventanaPacientes.main(tipoUsuario);


                } else {
                    JOptionPane.showMessageDialog(panelLogin, "Usuario/contraseña incorrectos", "Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
            }


    public static void main(String[] args) {
        login vLogin = new login();
        vLogin.setContentPane(vLogin.panelLogin);
        vLogin.setSize(300,300);
        vLogin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        vLogin.setVisible(true);
    }

    }
