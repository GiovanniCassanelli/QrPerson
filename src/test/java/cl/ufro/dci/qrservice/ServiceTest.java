package cl.ufro.dci.qrservice;

import cl.ufro.dci.qrservice.model.Usuario;
import cl.ufro.dci.qrservice.service.UsuarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServiceTest {

    private Usuario usuario;
    UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        usuarioService = new UsuarioService();
        usuarioService.cargarComunasDesdeCSV(); // Asegurarse de cargar las comunas antes de cada prueba
    }

    @Test
    @DisplayName("Validación nombre exitosa")
    public void validarNombreExitoso() {
        Usuario user = new Usuario();
        user.setNombre("Valeria");
        assertTrue(usuarioService.validarNombre(user));
    }

    @Test
    @DisplayName("Validación nombre - Nombre vacío")
    public void validarNombreVacio() {
        Usuario user = new Usuario();
        user.setNombre("");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.validarNombre(user);
        });
        assertEquals("El nombre no puede estar vacío.", exception.getMessage());
    }

    @Test
    @DisplayName("Validación nombre - Nombre nulo")
    public void validarNombreNulo() {
        Usuario user = new Usuario();
        user.setNombre(null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.validarNombre(user);
        });
        assertEquals("El nombre no puede estar vacío.", exception.getMessage());
    }

    @Test
    @DisplayName("Validación nombre - Nombre con caracteres no permitidos")
    public void validarNombreCaracteresNoPermitidos() {
        Usuario user = new Usuario();
        user.setNombre("1233"); // Nombre con caracteres no permitidos
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.validarNombre(user);
        });
        assertEquals("El nombre debe contener solo texto, máximo 30 letras y la primera letra en mayúscula.",
                exception.getMessage());
    }

    @Test
    @DisplayName("Validación nombre - Nombre excede longitud máxima")
    public void validarNombreExcedeLongitudMaxima() {
        Usuario user = new Usuario();
        user.setNombre("ValeriaValeriaValeriaValeriaValeriaValeriaValeria"); // Nombre excede longitud máxima
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.validarNombre(user);
        });
        assertEquals("El nombre debe contener solo texto, máximo 30 letras y la primera letra en mayúscula.",
                exception.getMessage());
    }

    @Test
    @DisplayName("Validación apellido paterno")
    public void validarApellidoPaterno() {
        Usuario user = new Usuario();
        user.setApellidoPaterno("Negrier");
        UsuarioService userService = new UsuarioService();
        Assertions.assertEquals(true, userService.validarApellidoPaterno(user));
    }

    @Test
    @DisplayName("Validación apellido materno")
    public void validarApellidoMaterno() {
        Usuario user = new Usuario();
        user.setApellidoMaterno("Seguel");
        UsuarioService userService = new UsuarioService();
        Assertions.assertEquals(true, userService.validarApellidoMaterno(user));
    }

    @Test
    @DisplayName("Validación apellido paterno - Apellido paterno vacío")
    public void validarApellidoPaternoVacio() {
        Usuario user = new Usuario();
        user.setApellidoPaterno("");
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarApellidoPaterno(user);
        });
        assertEquals("El apellido paterno no puede estar vacío.", exception.getMessage());
    }

    @Test
    @DisplayName("Validación apellido paterno - Apellido paterno nulo")
    public void validarApellidoPaternoNulo() {
        Usuario user = new Usuario();
        user.setApellidoPaterno(null);
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarApellidoPaterno(user);
        });
        assertEquals("El apellido paterno no puede estar vacío.", exception.getMessage());
    }

    @Test
    @DisplayName("Validación apellido paterno - Apellido paterno con caracteres no permitidos")
    public void validarApellidoPaternoCaracteresNoPermitidos() {
        Usuario user = new Usuario();
        user.setApellidoPaterno("1234"); // Apellido con caracteres no permitidos
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarApellidoPaterno(user);
        });
        assertEquals("El apellido debe contener solo texto, máximo 30 letras y la primera letra en mayúscula.",
                exception.getMessage());
    }

    @Test
    @DisplayName("Validación apellido paterno - Apellido paterno excede longitud máxima")
    public void validarApellidoPaternoExcedeLongitudMaxima() {
        Usuario user = new Usuario();
        user.setApellidoPaterno("NegrierNegrierNegrierNegrierNegrierNegrier"); // Apellido excede longitud máxima
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarApellidoPaterno(user);
        });
        assertEquals("El apellido debe contener solo texto, máximo 30 letras y la primera letra en mayúscula.",
                exception.getMessage());
    }

    // Pruebas para validarApellidoMaterno

    @Test
    @DisplayName("Validación apellido materno - Apellido materno vacío")
    public void validarApellidoMaternoVacio() {
        Usuario user = new Usuario();
        user.setApellidoMaterno("");
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarApellidoMaterno(user);
        });
        assertEquals("El apellido materno no puede estar vacío.", exception.getMessage());
    }

    @Test
    @DisplayName("Validación apellido materno - Apellido materno nulo")
    public void validarApellidoMaternoNulo() {
        Usuario user = new Usuario();
        user.setApellidoMaterno(null);
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarApellidoMaterno(user);
        });
        assertEquals("El apellido materno no puede estar vacío.", exception.getMessage());
    }

    @Test
    @DisplayName("Validación apellido materno - Apellido materno con caracteres no permitidos")
    public void validarApellidoMaternoCaracteresNoPermitidos() {
        Usuario user = new Usuario();
        user.setApellidoMaterno("1234"); // Apellido con caracteres no permitidos
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarApellidoMaterno(user);
        });
        assertEquals("El apellido debe contener solo texto, máximo 30 letras y la primera letra en mayúscula.",
                exception.getMessage());
    }

    @Test
    @DisplayName("Validación apellido materno - Apellido materno excede longitud máxima")
    public void validarApellidoMaternoExcedeLongitudMaxima() {
        Usuario user = new Usuario();
        user.setApellidoMaterno("SeguelSeguelSeguelSeguelSeguelSeguelSeguel"); // Apellido excede longitud máxima
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarApellidoMaterno(user);
        });
        assertEquals("El apellido debe contener solo texto, máximo 30 letras y la primera letra en mayúscula.",
                exception.getMessage());
    }

    @Test
    @DisplayName("Validación RUT exitoso")
    public void validarRutExitoso() {
        Usuario user = new Usuario();
        user.setRut("12.345.678-5");
        UsuarioService userService = new UsuarioService();
        Assertions.assertTrue(userService.validarRut(user));
    }

    @Test
    @DisplayName("Validación RUT vacío")
    public void validarRutVacio() {
        Usuario user = new Usuario();
        user.setRut("");
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarRut(user);
        });
        assertEquals("El RUT no puede estar vacío.", exception.getMessage());
    }

    @Test
    @DisplayName("Validación RUT nulo")
    public void validarRutNulo() {
        Usuario user = new Usuario();
        user.setRut(null);
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarRut(user);
        });
        assertEquals("El RUT no puede estar vacío.", exception.getMessage());
    }

    @Test
    @DisplayName("Validación RUT formato incorrecto")
    public void validarRutFormatoIncorrecto() {
        Usuario user = new Usuario();
        user.setRut("12345678-5"); // RUT con formato incorrecto
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarRut(user);
        });
        assertEquals("Ingrese el formato xx.xxx.xxx-x de RUT", exception.getMessage());
    }

    @Test
    @DisplayName("Validación RUT excede longitud máxima")
    public void validarRutExcedeLongitudMaxima() {
        Usuario user = new Usuario();
        user.setRut("12.345.678-123456"); // RUT con más de 12 dígitos
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarRut(user);
        });
        assertEquals("El RUT no puede exceder los 12 dígitos.", exception.getMessage());
    }

    @Test
    @DisplayName("Validación fecha de nacimiento - Formato incorrecto")
    public void validarFechaNacimientoFormatoIncorrecto() {
        Usuario user = new Usuario();
        user.setfNacimiento("1983-12-09"); // Formato incorrecto
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarFechaNacimientoUsuario(user);
        });
        assertEquals("La fecha no es válida, asegúrese de ingresar la fecha en formato dd-MM-yyyy",
                exception.getMessage());
    }

    @Test
    @DisplayName("Validación fecha de nacimiento - Menor de edad")
    public void validarFechaNacimientoMenorDeEdad() {
        Usuario user = new Usuario();
        user.setfNacimiento("09-12-2010"); // Menor de edad
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarFechaNacimientoUsuario(user);
        });
        assertEquals("El usuario debe ser mayor de edad", exception.getMessage());
    }

    @Test
    @DisplayName("Validación fecha de nacimiento - Fecha en el futuro")
    public void validarFechaNacimientoFuturo() {
        Usuario user = new Usuario();
        user.setfNacimiento("09-12-2025"); // Fecha en el futuro
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarFechaNacimientoUsuario(user);
        });
        assertEquals("El usuario debe ser mayor de edad", exception.getMessage());
    }

    @Test
    @DisplayName("Validación fecha de nacimiento - Justo 18 años")
    public void validarFechaNacimientoJusto18() {
        Usuario user = new Usuario();
        // Fecha de nacimiento para alguien que cumple 18 años hoy
        LocalDate today = LocalDate.now();
        LocalDate fechaNacimiento = today.minusYears(18);
        user.setfNacimiento(fechaNacimiento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        UsuarioService userService = new UsuarioService();
        Assertions.assertEquals(true, userService.validarFechaNacimientoUsuario(user));
    }

    @Test
    @DisplayName("Validación fecha de nacimiento - Éxito")
    public void validarFechaNacimientoExitoso() {
        Usuario user = new Usuario();
        user.setfNacimiento("09-12-1983");
        UsuarioService userService = new UsuarioService();
        Assertions.assertEquals(true, userService.validarFechaNacimientoUsuario(user));
    }

    @Test
    @DisplayName("Validación correo electrónico")
    public void validarCorreo() {
        Usuario user = new Usuario();
        user.setCorreo("valerrian@gmail.com");
        UsuarioService userService = new UsuarioService();
        userService.validarCorreo(user);
        Assertions.assertEquals(true, userService.validarCorreo(user));
    }

    @Test
    @DisplayName("Validación correo - correo válido con caracteres especiales")
    public void validarCorreoValidoConCaracteresEspeciales() {
        Usuario user = new Usuario();
        user.setCorreo("usuario.name+test@example.com");
        UsuarioService userService = new UsuarioService();
        Assertions.assertEquals(true, userService.validarCorreo(user));
    }

    @Test
    @DisplayName("Validación correo - correo válido con subdominio")
    public void validarCorreoValidoConSubdominio() {
        Usuario user = new Usuario();
        user.setCorreo("usuario@subdominio.example.com");
        UsuarioService userService = new UsuarioService();
        Assertions.assertEquals(true, userService.validarCorreo(user));
    }

    @Test
    @DisplayName("Validación correo - sin '@'")
    public void validarCorreoSinArroba() {
        Usuario user = new Usuario();
        user.setCorreo("usuariodominio.com");
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarCorreo(user);
        });
        assertEquals("Ingrese un correo del tipo: lorem@ipsum.cl", exception.getMessage());
    }

    @Test
    @DisplayName("Validación correo - sin dominio")
    public void validarCorreoSinDominio() {
        Usuario user = new Usuario();
        user.setCorreo("usuario@.com");
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarCorreo(user);
        });
        assertEquals("Ingrese un correo del tipo: lorem@ipsum.cl", exception.getMessage());
    }

    @Test
    @DisplayName("Validación correo - sin nombre de usuario")
    public void validarCorreoSinNombreDeUsuario() {
        Usuario user = new Usuario();
        user.setCorreo("@dominio.com");
        UsuarioService userService = new UsuarioService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.validarCorreo(user);
        });
        assertEquals("Ingrese un correo del tipo: lorem@ipsum.cl", exception.getMessage());
    }

    @Test
    @DisplayName("Validación ciudad - Éxito")
    public void validarCiudadExito() {
        Usuario user = new Usuario();
        user.setCiudad("Iquique");
        Assertions.assertTrue(usuarioService.validarCiudad(user));
    }

    @Test
    @DisplayName("Validación ciudad - Fallo: Ciudad no válida")
    public void validarCiudadNoValida() {
        Usuario user = new Usuario();
        user.setCiudad("CiudadInventada");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.validarCiudad(user);
        });
        assertEquals("La ciudad ingresada no es válida.", exception.getMessage());
    }

    @Test
    @DisplayName("Validación ciudad - Fallo: Ciudad con mayúsculas y minúsculas mixtas")
    public void validarCiudadMixta() {
        Usuario user = new Usuario();
        user.setCiudad("IQuIqUE");
        Assertions.assertTrue(usuarioService.validarCiudad(user));
    }

    @Test
    @DisplayName("Validación ciudad - Fallo: Ciudad vacía")
    public void validarCiudadVacia() {
        Usuario user = new Usuario();
        user.setCiudad("");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.validarCiudad(user);
        });
        assertEquals("La ciudad ingresada no es válida.", exception.getMessage());
    }

    @Test
    @DisplayName("Validación ciudad - Fallo: Ciudad nula")
    public void validarCiudadNula() {
        Usuario user = new Usuario();
        user.setCiudad(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.validarCiudad(user);
        });
        assertEquals("La ciudad ingresada no es válida.", exception.getMessage());
    }

    @Test
    @DisplayName("Validación ciudad - Fallo: Ciudad con caracteres especiales")
    public void validarCiudadCaracteresEspeciales() {
        Usuario user = new Usuario();
        user.setCiudad("Iqui@que");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.validarCiudad(user);
        });
        assertEquals("La ciudad ingresada no es válida.", exception.getMessage());
    }

    @Test
    @DisplayName("Texto Esperado")
    public void textoEsperado() {
        Usuario user = new Usuario();
        user.setRut("12.345.678-5");
        user.setNombre("Valeria");
        user.setApellidoPaterno("Negrier");
        user.setApellidoMaterno("Seguel");
        user.setCorreo("valerrian@gmail.com");
        user.setfNacimiento("09-12-1983");
        user.setCiudad("Temuco");

        System.out.println(user);
    }
}
