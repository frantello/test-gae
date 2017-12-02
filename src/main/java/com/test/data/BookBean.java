package com.test.data;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Test data bean
 */
@Entity
@Cache
@ApiModel("Book object")
public class BookBean {

    public static final String FIELD_ID = "id";

    public static final String FIELD_NAME = "name";

    public static final String FIELD_AUTHOR = "author";

    public static final String FIELD_YEAR = "year";

    public static final String FIELD_GENDER = "gender";

    @Id
    @ApiModelProperty(value = "Books's ID")
    private Long id;

    @ApiModelProperty(value = "Books's name", required = true)
    private String name;

    @ApiModelProperty(value = "Books's author", required = true)
    @Index
    private String author;

    @ApiModelProperty(value = "Books's year", required = true)
    private Integer year;

    @ApiModelProperty(value = "Books's gender", required = true)
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
