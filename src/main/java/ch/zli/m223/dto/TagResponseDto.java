
package ch.zli.m223.dto;

import ch.zli.m223.model.Tag;

public class TagResponseDto {

    public Long id;
    public String title;

    public static TagResponseDto fromEntity(Tag tag) {
        TagResponseDto dto = new TagResponseDto();

        dto.id = tag.getId();
        dto.title = tag.getTitle();

        return dto;
    }
}
