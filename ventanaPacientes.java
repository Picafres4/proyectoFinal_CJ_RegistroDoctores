import DAO.PacientesCRUD;
import entidades.Paciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ventanaPacientes extends JFrame {
    private JPanel miPanel;
    private JTextField txtIdPaciente;
    private JLabel lblidPaciente;
    private JLabel lblNombrePaciente;
    private JTextField txtNombre;
    private JLabel lblSexo;
    private JLabel lblApellidoP;
    private JTextField txtApellidoP;
    private JLabel lblApellidoM;
    private JTextField txtApellidoM;
    private JLabel lblNSS;
    private JTextField txtNSS;
    private JLabel lblOperaciones;
    private JTextField txtOperaciones;
    private JLabel lblAlergias;
    private JTextField txtAlergias;
    private JLabel lblPadecimientos;
    private JTextField txtPadecimientos;
    private JLabel lblFecha;
    private JComboBox cmbSexo;
    private JTextField txtFecha;
    private JButton btnBuscar;

    public JButton getBtnNuevo() {
        return btnNuevo;
    }

    public void setBtnNuevo(JButton btnNuevo) {
        this.btnNuevo = btnNuevo;
    }

    public JButton btnNuevo;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JPanel panelBotones;
    private JLabel lblTitulo;
    private JButton btnLimpiar;

    public boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha =
                    new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            Date miFecha = formatoFecha.parse(fecha);
            System.out.println(miFecha);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // limpiar formulario completo si no quiere dar de alta ningún paciente
    public void limpiarFormularioCompleto() {
        txtIdPaciente.setText(null);
        txtNombre.setText(null);
        txtApellidoM.setText(null);
        txtApellidoP.setText(null);
        txtAlergias.setText(null);
        txtOperaciones.setText(null);
        txtPadecimientos.setText(null);
        txtNSS.setText(null);
    }

    // limpiar formulario por si lo quiere dar de alta y que no estorbe la información del registro pasado pero
    // que se quede el ID
    public void limpiarFormulario() {
        txtNombre.setText(null);
        txtApellidoM.setText(null);
        txtApellidoP.setText(null);
        txtFecha.setText(null);
        txtAlergias.setText(null);
        txtOperaciones.setText(null);
        txtPadecimientos.setText(null);
        txtNSS.setText(null);
    }

    // verificar que no haya campos vacíos
    public boolean verificarCampo() {
        if (txtNombre.getText().isEmpty() || txtIdPaciente.getText().isEmpty() || txtNSS.getText().isEmpty() ||
                txtApellidoP.getText().isEmpty() || txtApellidoM.getText().isEmpty() || txtFecha.getText().isEmpty() ||
                txtOperaciones.getText().isEmpty() || txtAlergias.getText().isEmpty() ||
                txtPadecimientos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Los campos no están completados, inténtalo de nuevo");
            return true;
        } else {
            JOptionPane.showMessageDialog(miPanel, "Se registró el paciente con éxito");
            return false;
        }
    }

    // buscar
    public ventanaPacientes() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // buscar paciente

                PacientesCRUD crud = new PacientesCRUD();
                Paciente p = crud.buscarPacientePorId(txtIdPaciente.getText());

                // si es nulo mostrar este mensaje
                if (p == null) {
                    int respuesta = JOptionPane.showConfirmDialog(miPanel, "No se encuentra registrado el paciente " +
                                    " con el ID: " + txtIdPaciente.getText() + "\n¿quieres darlo de alta?",
                            "Paciente", JOptionPane.YES_NO_OPTION);

                    if (respuesta == 0) {
                        // si queremos dar de alta al alumno
                        limpiarFormulario();
                        btnNuevo.setEnabled(true);
                        txtNombre.requestFocus();

                    } else if (respuesta == 1) {
                        // no quiere dar de alta
                        limpiarFormularioCompleto();
                    }

                } else {
                    txtNombre.setText(p.getNombre());
                    txtApellidoP.setText(p.getApPaterno());
                    txtApellidoM.setText(p.getApMaterno());
                    txtNSS.setText(p.getNss());
                    txtOperaciones.setText(p.getOperacionesAnteriores());
                    txtAlergias.setText(p.getAlergias());
                    txtPadecimientos.setText(p.getPadecimientos());
                }
            }
        });
        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // instanciar objeto de la clase paciente para añadirlo a la lista
                Paciente p = new Paciente();

                p.setIdPaciente(txtIdPaciente.getText());
                p.setNombre(txtNombre.getText());
                p.setApPaterno(txtApellidoP.getText());
                p.setApMaterno(txtApellidoM.getText());
                p.setNss(txtNSS.getText());
                p.setOperacionesAnteriores(txtOperaciones.getText());
                p.setAlergias(txtAlergias.getText());
                p.setPadecimientos(txtPadecimientos.getText());

                p.setFechaNacimiento(new Date());

                boolean resultado = validarFecha(txtFecha.getText());
                if (resultado) {
                    JOptionPane.showMessageDialog(miPanel, "se agregó correctamente");

                }else {
                    JOptionPane.showMessageDialog(miPanel,"error en escritura, ingrese fecha de nuevo");
                    txtFecha.requestFocus();
                }

                // si los campos están bien agregados si se agrega el paciente
                if (verificarCampo() == false) {
                    PacientesCRUD crud = new PacientesCRUD();
                    crud.insertarPaciente(p);
                }
            }
        });

    }

    public static void main(String[] args) {
        ventanaPacientes v = new ventanaPacientes();
        v.setContentPane(v.miPanel);
        v.setSize(500, 500);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
    }
}
