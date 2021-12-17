package br.com.luizlmc.Dashboardvendas.controller;

import br.com.luizlmc.Dashboardvendas.dto.JournalEntryDTO;
import br.com.luizlmc.Dashboardvendas.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/journalentries")
public class JournalEntryController {

    @Autowired
    public JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<List<JournalEntryDTO>> search(){
        return ResponseEntity.ok().body(journalEntryService.search());
    }
}
