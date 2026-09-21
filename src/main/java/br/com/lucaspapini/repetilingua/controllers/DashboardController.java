package br.com.lucaspapini.repetilingua.controllers;

import br.com.lucaspapini.repetilingua.entities.dto.StatsDTO;
import br.com.lucaspapini.repetilingua.services.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController {
    @Autowired
    private TextService textService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/stats")
    public ResponseEntity<StatsDTO> getStats(){
        StatsDTO dto = textService.getAllStats();
        return ResponseEntity.ok(dto);
    }
}
