package ch.zli.m223.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.Entry;
import ch.zli.m223.service.EntryService;
import ch.zli.m223.dto.EntryRequestDto;
import ch.zli.m223.dto.EntryResponseDto;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController {

    @Inject
    EntryService entryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EntryResponseDto> index() {
    return entryService.findAll().stream()
            .map(EntryResponseDto::fromEntity)
            .toList();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EntryResponseDto create(EntryRequestDto dto) {

    validate(dto);

    Entry entry = new Entry();
    entry.setCheckIn(dto.checkIn);
    entry.setCheckOut(dto.checkOut);

    Entry created = entryService.createEntry(entry);

    return EntryResponseDto.fromEntity(created);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = entryService.deleteEntry(id);

        if (!deleted){
            throw new NotFoundException("Entry not Found!");
        }

    return Response.noContent().build();
    }

   @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EntryResponseDto update(
        @PathParam("id") Long id,
        EntryRequestDto dto) {

    validate(dto);

    Entry entry = new Entry();
    entry.setCheckIn(dto.checkIn);
    entry.setCheckOut(dto.checkOut);

    Entry updated = entryService.updateEntry(id, entry);

    if (updated == null) {
        throw new NotFoundException("Entry not found");
    }

    return EntryResponseDto.fromEntity(updated);
    }
   
    private void validate(EntryRequestDto dto) {

    if (dto == null ||
            dto.checkIn == null ||
            dto.checkOut == null) {
        throw new BadRequestException(
            "Check-in und Check-out sind Pflichtfelder"
        );
    }

    if (!dto.checkIn.isBefore(dto.checkOut)) {
        throw new BadRequestException(
            "Check-in muss vor Check-out liegen"
        );
    }
}
}

