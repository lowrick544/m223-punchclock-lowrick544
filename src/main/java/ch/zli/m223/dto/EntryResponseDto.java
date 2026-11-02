package ch.zli.m223.dto;

import java.time.LocalDateTime;
import ch.zli.m223.model.Entry;

public class EntryResponseDto {
    public Long id;
    public LocalDateTime checkIn;
    public LocalDateTime checkOut;
    public String username;


    public static EntryResponseDto fromEntity(Entry entry){
        EntryResponseDto dto = new EntryResponseDto();

        dto.id = entry.getId();
        dto.checkIn = entry.getCheckIn();
        dto.checkOut = entry.getCheckOut();

        dto.username = entry.getApplicationUser() != null
        ? entry.getApplicationUser().getUsername()
        : null;

        return dto;
    }
}