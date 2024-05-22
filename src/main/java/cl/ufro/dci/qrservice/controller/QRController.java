package cl.ufro.dci.qrservice.controller;

import cl.ufro.dci.qrservice.helpers.QRGenerator;
import cl.ufro.dci.qrservice.model.Usuario;
import cl.ufro.dci.qrservice.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


import java.awt.image.BufferedImage;

import java.util.logging.Logger;

@RestController
@RequestMapping("/qrcode")
public class QRController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> generateQRCode(@RequestBody String textMsg) throws Exception {

        UsuarioService usuarioService = new UsuarioService();
        String[] textoSeparado = textMsg.split(",");
        Usuario user = new Usuario();

        user.setRut(textoSeparado[0]);
        user.setApellidoPaterno(textoSeparado[1]);
        user.setApellidoMaterno(textoSeparado[2]);
        user.setNombre(textoSeparado[3]);
        user.setCorreo(textoSeparado[4]);
        user.setfNacimiento(textoSeparado[5]);
        user.setCiudad(textoSeparado[6]);

        usuarioService.validarRut(user);
        usuarioService.validarApellidoPaterno(user);
        usuarioService.validarApellidoMaterno(user);
        usuarioService.validarNombre(user);
        usuarioService.validarCorreo(user);
        usuarioService.validarFechaNacimientoUsuario(user);
        logger.info("response: " + textMsg);
        return okResponse(QRGenerator.generateQRCodeImage(textMsg));
    }

   
    private ResponseEntity<BufferedImage> okResponse(BufferedImage image) {
        return new ResponseEntity<>(image, HttpStatus.OK);
    }


    private static final Logger logger = Logger.getLogger(QRController.class.getName());

}
