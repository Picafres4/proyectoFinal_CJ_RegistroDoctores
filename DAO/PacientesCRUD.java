package DAO;

import entidades.Paciente;

import java.io.*;
import java.util.ArrayList;


public class PacientesCRUD {

    //método para leer la lista de Pacientes que no recibe ningún parámetro pero regresa el array list
    public static ArrayList<Paciente> leerListaPacientes() {
        Object o;
        try {
            FileInputStream leer =
                    new FileInputStream("athainarada\\listaPacientes.txt");
            ObjectInputStream miStream2 = new ObjectInputStream(leer);
            o = miStream2.readObject();
            ArrayList<Paciente> otraLista = (ArrayList<Paciente>) o;
            return otraLista;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("El archivo no existe: " + e.getMessage(), e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al leer el archivo: " + e.getMessage(), e);
        }
    }

    // método que recibe el array list que fue resultado del metodo leerListaPacientes()
    private static void escribirPacientes(ArrayList<Paciente>otraLista) {
        Object o;
        try {
        FileOutputStream escribir =
                new FileOutputStream("athainarada\\listaPacientes.txt");
        ObjectOutputStream miStream = new ObjectOutputStream(escribir);

            miStream.writeObject(otraLista);

        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo: " + e.getMessage(), e);
        }
    }

    // método de insertar paciente a su menú
    public void insertarPaciente(Paciente p) {
        ArrayList<Paciente> otraLista = leerListaPacientes();
        otraLista.add(p);
        escribirPacientes(otraLista);
    }

    // regresará un objeto de tipo Paciente si lo buscamos por su ID
    public Paciente buscarPacientePorId(String idPaciente) {
        ArrayList<Paciente> otraLista = leerListaPacientes();

        // lo guarda en otra variable y lo compara.
        for(Paciente p: otraLista){
            if(p.getIdPaciente().equals(idPaciente)){
                return p;
            }
        }
        //saliendo del for
        return null;

    }


}

