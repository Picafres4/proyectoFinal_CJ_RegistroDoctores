import javax.swing.*;

import DAO.DoctoresCRUD;
import entidades.Doctor;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventanaDoctores extends JFrame {
    private JTextField txtNom;
    private JLabel lblNombreDoctor;
    private JLabel lblApPat;
    private JLabel lblApMat;
    private JTextField txtApMat;
    private JTextField txtApPat;
    private JLabel lblTitulo;
    private JLabel lblSexo;
    private JComboBox cmbSex;
    private JLabel lblCed;
    private JTextField txtCed;
    private JLabel lblEsp;
    private JPanel jpTitulo;
    private JPanel jpBtn;
    private JButton btnBuscarDoc;
    private JButton btnAgrega;
    private JButton btnAct;
    private JButton btnEli;
    private JLabel lblIdDoctor;
    private JTextField txtIdDoc;
    private JPanel miPanelDoc;
    private JTextField txtEspecialidad;


    public boolean verificarCampoDoctores() {
        if (txtNom.getText().isEmpty() || txtApMat.getText().isEmpty() || txtEspecialidad.getText().isEmpty() ||txtIdDoc.getText().isEmpty() || txtApPat.getText().isEmpty() ||
                txtCed.getText().isEmpty()) {
            JOptionPane.showMessageDialog(miPanelDoc, "Los campos no están completados, inténtalo de nuevo");
            return true;
        } else {
            JOptionPane.showMessageDialog(miPanelDoc, "Se registró el doctor con éxito");
            return false;
        }
    }
    public void limpiarFormularioCompletoDoctores() {
        txtIdDoc.setText(null);
        txtNom.setText(null);
        txtApMat.setText(null);
        txtApPat.setText(null);
        txtCed.setText(null);
        txtEspecialidad.setText(null);
    }

    // limpiar formulario por si lo quiere dar de alta y que no estorbe la información del registro pasado pero
    // que se quede el ID
    public void limpiarFormularioDoctores() {
        txtNom.setText(null);
        txtApMat.setText(null);
        txtApPat.setText(null);
        txtCed.setText(null);
        txtEspecialidad.setText(null);
    }
    public ventanaDoctores() {
        btnBuscarDoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // buscar doctor

                DoctoresCRUD crud = new DoctoresCRUD();
                Doctor d = crud.buscarDoctorPorId(txtIdDoc.getText());


                // si es nulo mostrar este mensaje
                if (d == null) {
                    int respuesta = JOptionPane.showConfirmDialog(miPanelDoc, "No se encuentra registrado el doctor " +
                                    " con el ID: " + txtIdDoc.getText() + "\n¿quieres darlo de alta?",
                            "Doctor", JOptionPane.YES_NO_OPTION);

                    if (respuesta == 0) {
                        // si queremos dar de alta al doctor
                        limpiarFormularioDoctores();
                        btnAgrega.setEnabled(true);
                        txtNom.requestFocus();

                    } else if (respuesta == 1) {
                        // no quiere dar de alta
                        limpiarFormularioCompletoDoctores();
                    }

                    // sino llenarlo
                } else {
                    txtIdDoc.setText(d.getIdDoc());
                    txtNom.setText(d.getNombreDoc());
                    txtApPat.setText(d.getApPaternoDoc());
                    txtApMat.setText(d.getApMaternoDoc());
                    txtCed.setText(d.getCPDoc());
                    txtEspecialidad.setText(d.getEspecialidadDoc());

                }
            }
        });

        btnAgrega.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // instanciar objeto de la clase doctor para añadirlo a la lista
                Doctor d = new Doctor();

                d.setIdDoc(txtIdDoc.getText());
                d.setNombreDoc(txtNom.getText());
                d.setApPaternoDoc(txtApPat.getText());
                d.setApMaternoDoc(txtApMat.getText());
                d.setCPDoc(txtCed.getText());
                d.setEspecialidadDoc(txtEspecialidad.getText());

                // si los campos están bien agregados si se agrega el doctor
                if (verificarCampoDoctores() == false) {
                    DoctoresCRUD crud = new DoctoresCRUD();
                    crud.insertarDoctor(d);
                }
            }
        });
    }
        public static void main (String[]args){
            ventanaDoctores vD = new ventanaDoctores();
            vD.setContentPane(vD.miPanelDoc);
            vD.setSize(500, 500);
            vD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            vD.setVisible(true);
        }

}
