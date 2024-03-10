package io.umid.supportservice.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PageRequestDto {

    private int page = 0;
    private int size = 5;
    private String sortBy = "id";
    private Sort.Direction direction = Sort.Direction.ASC;

    public void setDirection(String direction) {
        this.direction = Sort.Direction.fromString(direction);
    }
}
