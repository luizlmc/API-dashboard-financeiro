package br.com.luizlmc.DashboardFinanceiro.serviceImpl;

import br.com.luizlmc.DashboardFinanceiro.dto.CategoryDTO;
import br.com.luizlmc.DashboardFinanceiro.dto.mapper.CategoryMapper;
import br.com.luizlmc.DashboardFinanceiro.model.Category;
import br.com.luizlmc.DashboardFinanceiro.repository.CategoryRepository;
import br.com.luizlmc.DashboardFinanceiro.serviceImpl.serviceImpl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.luizlmc.DashboardFinanceiro.builders.CategoryBuilder.aCategory;
import static br.com.luizlmc.DashboardFinanceiro.builders.CategoryDTOBuilder.*;
import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Garante o funcionamento da classe categoria service")
class CategoryServiceImplTest {

    private static final Long ID = 1L;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private ApplicationEventPublisher publisher;

    @Mock
    private MockHttpServletResponse response;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    @DisplayName("Deve listar todas as categorias")
    void mustListAllCategories () {
        List<Category> categories = Arrays.asList(
                aCategory().now(),
                aCategory().now()
        );

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoriesDTO = categoryService.findAll();

        assertAll(
                ()-> assertEquals(categories.stream().map(categoryMapper::toCategoryDTO).collect(Collectors.toList()), categoriesDTO),
                ()-> assertEquals(categories.size(), categoriesDTO.size())
        );
    }

    @Test
    @DisplayName("Deve criar uma nova categoria")
    void mustCreatedCategory() {

        Category category = aCategory().now();
        CategoryDTO categoryDTO = aCategoryDTO().now();

        when(categoryRepository.save(any())).thenReturn(category);
        when(categoryMapper.toCategoryDTO(any())).thenReturn(categoryDTO);
        doNothing().when(publisher).publishEvent(any());

        CategoryDTO categoryDTOResponse = categoryService.createdCategory(categoryDTO, response);

        assertEquals(categoryDTO, categoryDTOResponse);
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve buscar uma categoria por id")
    void mustFindById() {
        Category category = aCategory().now();
        CategoryDTO categoryDTO = aCategoryDTO().now();

        when(categoryRepository.findById(any())).thenReturn(ofNullable(category));
        when(categoryMapper.toCategoryDTO(any())).thenReturn(categoryDTO);

        Optional<CategoryDTO> categoryDTO2 = categoryService.findById(ID);

        assertTrue(categoryDTO2.isPresent());
        assertAll(
                ()->assertEquals(categoryDTO.getId(),categoryDTO2.get().getId()),
                ()->assertEquals(categoryDTO.getName(),categoryDTO2.get().getName())
        );
    }
}