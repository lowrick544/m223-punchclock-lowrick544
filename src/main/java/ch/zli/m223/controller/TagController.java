
package ch.zli.m223.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import ch.zli.m223.model.Tag;
import ch.zli.m223.service.TagService;
import ch.zli.m223.dto.TagRequestDto;
import ch.zli.m223.dto.TagResponseDto;

@Path("/tags")
@Produces(MediaType.APPLICATION_JSON)
public class TagController {

    @Inject
    TagService tagService;

    @GET
    public List<TagResponseDto> findAll() {
        return tagService.findAll().stream()
                .map(TagResponseDto::fromEntity)
                .toList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public TagResponseDto create(TagRequestDto dto) {
        validate(dto);

        Tag created = tagService.create(dto.title);

        return TagResponseDto.fromEntity(created);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public TagResponseDto update(
            @PathParam("id") Long id,
            TagRequestDto dto) {

        validate(dto);

        Tag updated = tagService.update(id, dto.title);

        return TagResponseDto.fromEntity(updated);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        tagService.delete(id);

        return Response.noContent().build();
    }

    private void validate(TagRequestDto dto) {
        if (dto == null || dto.title == null || dto.title.isBlank()) {
            throw new BadRequestException("Tag title is required");
        }
    }
}
