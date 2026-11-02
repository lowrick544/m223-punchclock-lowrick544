package ch.zli.m223.dto;
import java.util.List;

import java.time.LocalDateTime;

public class EntryRequestDto {

    public LocalDateTime checkIn;
    public LocalDateTime checkOut;
    public Long categoryId;
    public List<Long> tagIds;
}
