package com.example.RIS.turno.control;

import com.example.RIS.insumo.control.InsumoService;
import com.example.RIS.turno.model.Turno;
import com.example.RIS.turno.model.TurnoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    @Autowired
    private TurnoRespository turnoRespository;

    @Autowired
    private InsumoService insumoService;

    // Método para listar turnos
    public List<Turno> listarTurnos() {
        return turnoRespository.findAll();
    }

    public Turno crearTurno(Turno turno) {
        if (turno.getInsumo() != null) {
            insumoService.decrementarStock(turno.getInsumo().getId());
        }
        return turnoRespository.save(turno);
    }

    // Método para editar un turno
    public Turno editarTurno(Long id, Turno turnoActualizado) {
        return turnoRespository.findById(id).map(turno -> {
            turno.setPaciente(turnoActualizado.getPaciente());
            turno.setFechaHora(turnoActualizado.getFechaHora());
            turno.setDoctor(turnoActualizado.getDoctor());

            // Actualizamos el insumo si es diferente del anterior
            if (turno.getInsumo() != null && turnoActualizado.getInsumo() != null
                    && !turno.getInsumo().getId().equals(turnoActualizado.getInsumo().getId())) {
                insumoService.decrementarStock(turnoActualizado.getInsumo().getId());
            }

            turno.setInsumo(turnoActualizado.getInsumo());
            return turnoRespository.save(turno);
        }).orElseThrow(() -> new RuntimeException("Turno no encontrado con el ID: " + id));
    }
}


