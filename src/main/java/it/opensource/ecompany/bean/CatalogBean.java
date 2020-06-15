package it.opensource.ecompany.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.opensource.ecompany.domain.Category;
import it.opensource.ecompany.service.CategoriesService;

/**
 * 
 * 
 *  @author Paolo Bertin
 */
public class CatalogBean {

    private List<Category>    categories;

    private Integer           rowIndex;

    private Integer           numberColumns;

    @Autowired
    private CategoriesService categoriesService;

    public List<Category> getCategories() {

        setCategories(categoriesService.getAll());

        return  categories;
    }

    public void setCategories(List<Category> categories) {

        this.categories = categories;
    }

    public Integer getRowIndex() {

        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {

        this.rowIndex = rowIndex;
    }

    public Integer getNumberColumns() {

        return numberColumns;
    }

    public void setNumberColumns(Integer numberColumns) {

        this.numberColumns = numberColumns;
    }

}
