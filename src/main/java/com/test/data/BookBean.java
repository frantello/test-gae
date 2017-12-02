package com.test.data;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.wordnik.swagger.annotations.ApiModel;

/**
 * Test data bean
 */
@Entity
@Cache
@ApiModel("Bean object")
public class BookBean {

    public static final String FIELD_ID = "id";

    public static final String FIELD_NAME = "name";

    public static final String FIELD_AUTHOR = "author";

    public static final String FIELD_YEAR = "year";

    public static final String FIELD_GENDER = "gender";

    @Id
    private Long id;

    private String name;

    @Index
    private String author;

    private Integer year;

    private String gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
