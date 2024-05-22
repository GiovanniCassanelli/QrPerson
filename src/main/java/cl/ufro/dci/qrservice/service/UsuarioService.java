package cl.ufro.dci.qrservice.service;

import cl.ufro.dci.qrservice.model.Usuario;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


@Service
public class UsuarioService {
    private List<Usuario> usuarios;
    private Map<String, String> comunas;

    public UsuarioService() {
        usuarios = new ArrayList<>();
    }

    public void cargarComunasDesdeCSV() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/Dataset/comunas.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            comunas = new HashMap<>();

            // Saltar la primera línea (encabezados)
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String nombreComuna = parts[5]; // Obtener el nombre de la comuna
                comunas.put(nombreComuna.toLowerCase(), nombreComuna); // Almacenar en minúsculas para comparaciones más fáciles
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean validarCiudad(Usuario user) {
        cargarComunasDesdeCSV();
        String ciudad = user.getCiudad();
        if (comunas.containsKey(ciudad.toLowerCase())) {
            System.out.println("Ciudad válida...");
            return true;
        } else {
            throw new IllegalArgumentException("La ciudad ingresada no es válida.");
        }
    }

    public boolean validarNombre(Usuario user) {
        String nombre = user.getNombre();

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }

        String regex = "^[A-Z][a-zA-Z ]{0,29}$";

        if (nombre.matches(regex)) {
            System.out.println("Nombre válido...");
            return true;
        } else {
            throw new IllegalArgumentException(
                    "El nombre debe contener solo texto, máximo 30 letras y la primera letra en mayúscula.");
        }
    }

    public boolean validarApellidoPaterno(Usuario user) {
        String apellidoPaterno = user.getApellidoPaterno();

        if (apellidoPaterno == null || apellidoPaterno.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido paterno no puede estar vacío.");
        }

        String regex = "^[A-Z][a-zA-Z ]{0,29}$";

        if (apellidoPaterno.matches(regex)) {
            System.out.println("Apellido paterno válido...");
            return true;
        } else {
            throw new IllegalArgumentException(
                    "El apellido debe contener solo texto, máximo 30 letras y la primera letra en mayúscula.");
        }
    }

    public boolean validarApellidoMaterno(Usuario user) {
        String apellidoMaterno = user.getApellidoMaterno();

        if (apellidoMaterno == null || apellidoMaterno.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido materno no puede estar vacío.");
        }

        String regex = "^[A-Z][a-zA-Z ]{0,29}$";

        if (apellidoMaterno.matches(regex)) {
            System.out.println("Apellido materno válido...");
            return true;
        } else {
            throw new IllegalArgumentException(
                    "El apellido debe contener solo texto, máximo 30 letras y la primera letra en mayúscula.");
        }
    }

    public boolean validarRut(Usuario user) {
        String rut = user.getRut();

        if (rut == null || rut.isEmpty()) {
            throw new IllegalArgumentException("El RUT no puede estar vacío.");
        }

        Pattern pattern = Pattern.compile("^\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]{1}$");
        Matcher matcher = pattern.matcher(rut);

        if (rut.length() > 12) {
            throw new IllegalArgumentException("El RUT no puede exceder los 12 dígitos.");
        }

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Ingrese el formato xx.xxx.xxx-x de RUT");
        }

        return true;
    }

    public boolean validarFechaNacimientoUsuario(Usuario user) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$";
        if (user.getfNacimiento().matches(regex)) {
            LocalDate fechaNacimiento = LocalDate.parse(user.getfNacimiento(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDate fechaActual = LocalDate.now();
            Period edad = Period.between(fechaNacimiento, fechaActual);
            if (edad.getYears() >= 18) {
                System.out.println("Fecha de nacimiento válida y mayor de edad");
                return true;
            } else {
                throw new IllegalArgumentException("El usuario debe ser mayor de edad");
            }
        } else {
            throw new IllegalArgumentException(
                    "La fecha no es válida, asegúrese de ingresar la fecha en formato dd-MM-yyyy");
        }
    }

    public boolean validarCorreo(Usuario user) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(user.getCorreo());
        if (matcher.find()) {
            System.out.println("Correo válido...");
            return true;
        } else {
            throw new IllegalArgumentException("Ingrese un correo del tipo: lorem@ipsum.cl");
        }

    }

}
