package com.example.RIS.insumo.control;

import com.example.RIS.insumo.model.Insumo;
import com.example.RIS.insumo.model.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsumoService {
    @Autowired
    private InsumoRepository insumoRepository;
    public List<Insumo> listarTodosLosInsumos() {
        return insumoRepository.findAll();
    }
    public void decrementarStock(Long insumoId) {
        Insumo insumo = insumoRepository.findById(insumoId)
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));
        if (insumo.getStock() <= 0) {
            throw new RuntimeException("Stock insuficiente para el insumo: " + insumo.getNombre());
        }
        insumo.setStock(insumo.getStock() - 1);
        if (insumo.getStock() < 5) {
            // Lógica de alerta (puedes enviar un correo, una notificación, etc.)
            System.out.println("Alerta: Stock bajo para el insumo: " + insumo.getNombre());
        }
        insumoRepository.save(insumo);
    }

}

