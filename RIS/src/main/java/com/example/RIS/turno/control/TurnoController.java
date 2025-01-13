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

    @PostMapping
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.crearTurno(turno));
    }

    @GetMapping
    public List<Turno> listarTurnos() {
        return turnoService.listarTurnos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno> editarTurno(@PathVariable Long id, @RequestBody Turno turnoActualizado) {
        return ResponseEntity.ok(turnoService.editarTurno(id, turnoActualizado));
    }
}


