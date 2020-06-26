package it.opensource.ecompany.web.restcontroller.util;

import java.util.List;

import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageWrapper<T> {
    
    private List<T> content;
    private Boolean last;
    private Boolean first;
    private Integer totalPages;
    private Integer totalElements;
    private Integer size;
    private Integer number;
    private Integer numberOfElements;
    private Sort    sort;
}