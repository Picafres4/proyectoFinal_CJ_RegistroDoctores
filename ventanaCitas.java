import DAO.CitaCRUD;
import DAO.DoctoresCRUD;
import DAO.PacientesCRUD;
import entidades.Cita;
import entidades.Doctor;
import entidades.Paciente;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ventanaCitas extends JFrame{


    private JPanel txtTituloCitas;
    private JLabel lblCitas;
    private JLabel lblIdPacienteCita;
    private JLabel lblEspecialista;
    private JComboBox cmbEspecialista;
    private JLabel lblFecha;
    private JButton btnAgendarCita;
    private JButton btnCancelarCita;
    private JComboBox cmbId;
    private JPanel panelCitas;
    private JLabel lbObservaciones;
    private JTextArea txtAObservaciones;
    private JTextField txtNombreCompleto;
    private JTextField txtFecha;
    private JLabel lblHora;
    private JComboBox cmbHora;
    private JComboBox cmbMinutos;

    ArrayList<Paciente> listaPacientes;
    ArrayList<Doctor> listaDoctores;

    ArrayList<Cita> listaCitas;

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

    public void siSeValida() {
        if (validarFecha(txtFecha.getText())) {
            boolean resultado = true;
            if (resultado) {
                JOptionPane.showMessageDialog(panelCitas, "se agregó correctamente");

            } else {
                JOptionPane.showMessageDialog(panelCitas, "error en escritura, ingrese fecha de nuevo");
                txtFecha.requestFocus();
            }
        }
    }

    public ventanaCitas(){

        nombreDoctores();
        String nombreDoctor= cmbEspecialista.getSelectedItem().toString();

        busquedaIdPaciente();
        String idPacienteM = cmbId.getSelectedItem().toString();

        //busquedaFecha();
        //String busquedaFecha = txtFecha.getText();
        cmbId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombreCompleto = "";
                Object selectedItem = cmbId.getSelectedItem();

                if (selectedItem != null) {
                    String idPaciente = selectedItem.toString();

                }

                for (Paciente p: listaPacientes){
                    if (p.getIdPaciente().equals(selectedItem)) {
                        nombreCompleto = p.getNombre() + " " + p.getApPaterno();
                        break;

                    }
                txtNombreCompleto.setText(nombreCompleto);
                }

            }
        });
        btnAgendarCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                validarFecha(txtFecha.getText());
                siSeValida();

                Cita c = new Cita();

                c.setIdDoctor(cmbEspecialista.getSelectedItem().toString());
                c.setIdPaciente(cmbId.getSelectedItem().toString());
                c.setHora(cmbHora.getSelectedItem().toString());
                c.setMinutos(cmbMinutos.getSelectedItem().toString());
                c.setFecha(txtFecha.getText());
                c.setObservaciones(txtAObservaciones.getText());

                CitaCRUD crud = new CitaCRUD();
                crud.insertarCita(c);

            }


        });
}


    public void nombreDoctores() {
        listaDoctores = DoctoresCRUD.leerListaDoctores();
        listaDoctores.remove(0);

        listaDoctores.get(0);
        //leer la lista de los pacientes

        for(Doctor d : listaDoctores){
            cmbEspecialista.addItem(d.getNombreDoc());

        }
    }

    public void busquedaIdPaciente() {
        listaPacientes = PacientesCRUD.leerListaPacientes();

        listaDoctores.get(0);
        //leer la lista de los pacientes

        for(Paciente p : listaPacientes){
            cmbId.addItem(p.getIdPaciente());

        }}

    public void busquedaFecha() {
        listaCitas = CitaCRUD.leerListaCitas();

        listaCitas.get(0);
        //leer la lista de los pacientes

        for(Cita c : listaCitas){
            cmbId.addItem(c.getFecha());

        }}
    public static void main(String[] args) {
        ventanaCitas t = new ventanaCitas();
        t.setContentPane(t.panelCitas);
        t.setSize(500, 500);
        t.setTitle("Ventana para agregar cita");
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.setVisible(true);


    }
}
