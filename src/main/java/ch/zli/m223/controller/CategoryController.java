
package ch.zli.m223.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import ch.zli.m223.model.Category;
import ch.zli.m223.service.CategoryService;
import ch.zli.m223.dto.CategoryRequestDto;
import ch.zli.m223.dto.CategoryResponseDto;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryController {

    @Inject
    CategoryService categoryService;

    @GET
    public List<CategoryResponseDto> findAll() {
        return categoryService.findAll().stream()
                .map(CategoryResponseDto::fromEntity)
                .toList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public CategoryResponseDto create(CategoryRequestDto dto) {
        validate(dto);

        Category created = categoryService.create(dto.title);

        return CategoryResponseDto.fromEntity(created);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public CategoryResponseDto update(
            @PathParam("id") Long id,
            CategoryRequestDto dto) {

        validate(dto);

        Category updated = categoryService.update(id, dto.title);

        return CategoryResponseDto.fromEntity(updated);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        categoryService.delete(id);

        return Response.noContent().build();
    }

    private void validate(CategoryRequestDto dto) {
        if (dto == null || dto.title == null || dto.title.isBlank()) {
            throw new BadRequestException("Category title is required");
        }
    }
}
