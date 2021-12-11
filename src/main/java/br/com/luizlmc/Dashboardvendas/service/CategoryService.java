package br.com.luizlmc.Dashboardvendas.service;

import br.com.luizlmc.Dashboardvendas.dto.CategoryDTO;
import br.com.luizlmc.Dashboardvendas.dto.mapper.CategoryMapper;
import br.com.luizlmc.Dashboardvendas.event.ResourceCreatedEvent;
import br.com.luizlmc.Dashboardvendas.model.Category;
import br.com.luizlmc.Dashboardvendas.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ApplicationEventPublisher publisher;

    public List<CategoryDTO> findAll(){
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryDTO).collect(Collectors.toList());
    }

    public CategoryDTO createdCategory(CategoryDTO categoryDTO, HttpServletResponse response) {
        Category categorySave = categoryRepository.save(categoryMapper.toCategory(categoryDTO));

        publisher.publishEvent(new ResourceCreatedEvent(this, response, categorySave.getId()));
        return categoryMapper.toCategoryDTO(categorySave);
    }

    public Optional<CategoryDTO> findById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toCategoryDTO);
    }
}
