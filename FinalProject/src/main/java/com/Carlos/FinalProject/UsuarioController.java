package com.Carlos.FinalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop/e-commerce/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/actual")
    public ResponseEntity<Usuario> getUsuarioActual(@RequestParam String userName) {
        Usuario usuario = usuarioService.verificarUsuario(userName);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/verificar")
    public ResponseEntity<Usuario> verificarUsuario(@RequestBody UsuarioNameRequest usuarioNameRequest) {
        Usuario usuario = usuarioService.verificarUsuario(usuarioNameRequest.getNombre().trim());
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/eliminarTodos")
    public ResponseEntity<String> eliminarTodosLosUsuarios() {
        usuarioRepository.deleteAll();
        return ResponseEntity.ok("Todos los usuarios han sido eliminados.");
    }
}