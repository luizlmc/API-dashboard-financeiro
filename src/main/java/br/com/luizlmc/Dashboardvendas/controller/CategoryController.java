package br.com.luizlmc.Dashboardvendas.controller;

import br.com.luizlmc.Dashboardvendas.dto.CategoryDTO;
import br.com.luizlmc.Dashboardvendas.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody @Validated CategoryDTO categoryDTO, HttpServletResponse response) {

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createdCategory(categoryDTO, response));
    }
}
