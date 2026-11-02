
package ch.zli.m223.dto;

import ch.zli.m223.model.Category;

public class CategoryResponseDto {

    public Long id;
    public String title;

    public static CategoryResponseDto fromEntity(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();

        dto.id = category.getId();
        dto.title = category.getTitle();

        return dto;
    }
}
