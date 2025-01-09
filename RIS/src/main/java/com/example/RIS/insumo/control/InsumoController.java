package com.example.RIS.insumo.control;

import com.example.RIS.insumo.model.Insumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/insumos")
public class InsumoController {
    @Autowired
    private InsumoService insumoService;

    @GetMapping("/reporte")
    public ResponseEntity<List<Insumo>> generarReporteInsumos() {
        List<Insumo> insumos = insumoService.listarTodosLosInsumos();
        if (insumos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 si no hay insumos
        }
        return ResponseEntity.ok(insumos); // Retorna 200 con la lista de insumos
    }

}

