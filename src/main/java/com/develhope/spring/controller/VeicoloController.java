package com.develhope.spring.controller;
import com.develhope.spring.entity.Veicolo;
import com.develhope.spring.service.VeicoloService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/veicolo")
public class VeicoloController {
    @Autowired
    private VeicoloService veicoloService;

    @PostMapping("/addVeicolo")
    public ResponseEntity<String> addVeicolo(@RequestBody Veicolo veicolo) {
        veicoloService.addVeicolo(veicolo);
        return ResponseEntity.ok("Veicolo aggiunto correttamente");
    }
}
