package com.onlineauction.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CategoryInfo {

    Long id;
    
    String name;
    
    String description;
    
    CategoryInfo parentCategory;
    
    List<CategoryInfo> childCategories;
    
}
