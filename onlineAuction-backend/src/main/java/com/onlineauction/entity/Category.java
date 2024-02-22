package com.onlineauction.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@FieldDefaults(level=AccessLevel.PRIVATE)
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
     Integer categoryId;

    @Column(name = "category_name")
     String categoryName;

    @Column(name = "category_description")
     String categoryDescription;


    @Column(name="parent_category_id")
    Integer parentCategoryId;
    
    @JsonIgnore
    @OneToMany(targetEntity = Category.class, cascade = CascadeType.ALL)
    @JoinColumn(name="category_id",referencedColumnName = "category_id")
     List<Category> subCategories;
    
}