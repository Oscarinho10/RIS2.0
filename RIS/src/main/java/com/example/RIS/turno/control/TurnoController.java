package com.example.RIS.turno.control;

import com.example.RIS.turno.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
@CrossOrigin(origins = "http://127.0.0.1:5500")  // Allow CORS for this controller
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    // Crear un nuevo turno (POST)
    @PostMapping
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.crearTurno(turno));
    }

    // Listar todos los turnos (GET)
    @GetMapping
    public List<Turno> listarTurnos() {
        return turnoService.listarTurnos();
    }

    // Obtener un turno por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Turno> obtenerTurno(@PathVariable Long id) {
        Turno turno = turnoService.obtenerTurnoPorId(id);
        if (turno != null) {
            return ResponseEntity.ok(turno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Editar un turno existente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Turno> editarTurno(@PathVariable Long id, @RequestBody Turno turnoActualizado) {
        Turno turno = turnoService.editarTurno(id, turnoActualizado);
        if (turno != null) {
            return ResponseEntity.ok(turno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
