package com.vehiculos.VehiculosRest.controllers;

import com.vehiculos.VehiculosRest.models.VehiculoModel;
import com.vehiculos.VehiculosRest.services.VehiculoService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    // GET todos los vehículos
    @GetMapping
    public ArrayList<VehiculoModel> getVehiculos() {
        return this.vehiculoService.getVehiculos();
    }

    // GET vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<VehiculoModel> getVehiculoById(@PathVariable Long id) {
        Optional<VehiculoModel> vehiculo = this.vehiculoService.getbyId(id);
        return vehiculo.map(v -> new ResponseEntity<>(v, HttpStatus.OK))
                       .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST crear vehículo
    @PostMapping
    public ResponseEntity<VehiculoModel> createVehiculo(@RequestBody VehiculoModel vehiculo) {
        VehiculoModel saved = this.vehiculoService.saveAuto(vehiculo);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // PUT actualizar vehículo
    @PutMapping("/{id}")
    public ResponseEntity<VehiculoModel> updateVehiculo(@PathVariable Long id, @RequestBody VehiculoModel vehiculo) {
        try {
            VehiculoModel updated = this.vehiculoService.updateById(vehiculo, id);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE eliminar vehículo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehiculo(@PathVariable Long id) {
        try {
            this.vehiculoService.deleteAuto(id);
            return new ResponseEntity<>("Vehículo eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Vehículo no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}