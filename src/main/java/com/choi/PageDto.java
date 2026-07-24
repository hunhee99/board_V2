package com.choi;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class PageDto {
    private int page;
    private int pageSize;
    private int totalItems;
    private List<WiseSaying> content;





    public int getTotalPages(){
        if (totalItems == 0){ return 1; }
        return (totalItems + pageSize - 1) / pageSize;
    }
}
