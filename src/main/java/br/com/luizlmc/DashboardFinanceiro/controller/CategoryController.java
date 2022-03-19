package br.com.luizlmc.DashboardFinanceiro.controller;

import br.com.luizlmc.DashboardFinanceiro.dto.CategoryDTO;
import br.com.luizlmc.DashboardFinanceiro.service.serviceImpl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY') and hasAuthority('SCOPE_read')")
    public ResponseEntity<List<CategoryDTO>> listAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY') and hasAuthority('SCOPE_read')")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_REGISTER_CATEGORY') and hasAuthority('SCOPE_write')")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Validated CategoryDTO categoryDTO, HttpServletResponse response) {

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createdCategory(categoryDTO, response));
    }
}
