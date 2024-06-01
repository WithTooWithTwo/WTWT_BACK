package wtwt.domain.category.presentation;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wtwt.common.doc.swagger.CategorySwagger;
import wtwt.common.dto.response.IdResponse;
import wtwt.domain.category.application.CategoryService;
import wtwt.domain.category.dto.CategorySummary;
import wtwt.domain.category.presentation.dto.request.CreateCategoryApiReq;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController implements CategorySwagger {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<IdResponse> create(
        @RequestBody @Valid CreateCategoryApiReq request
    ) {
        CategorySummary response = categoryService.create(request.toCreateCategoryReq());
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/category/{id}")
            .buildAndExpand(response.id())
            .toUri();

        return ResponseEntity.created(uri).body(
            IdResponse.from(response.id())
        );
    }

    @GetMapping("/main")
    public ResponseEntity<List<CategorySummary>> findMainCategories() {
        List<CategorySummary> response = categoryService.findMainCategories();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CategorySummary>> findAllByParent(Long parentId) {
        List<CategorySummary> response = categoryService.findAllByParent(parentId);
        return ResponseEntity.ok(response);
    }
}
