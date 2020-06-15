package it.opensource.ecompany.web.form;

import it.opensource.ecompany.domain.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductForm {

    private String   name;

    private String   description;

    private String   isbn;

    private Float    price;

    private byte[]   image;

    private Category category;

    private Long     version;

}
