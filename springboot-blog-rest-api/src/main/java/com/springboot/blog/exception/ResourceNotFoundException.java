package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//custom exception
//aim: the APIs will throw a ResourceNotFoundException whenever a Post with a given id is not found in the database
@ResponseStatus(value = HttpStatus.NOT_FOUND)
//cause Springboot to respond with the specified Http status code whenever exception is thrown from controller
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));//post not found with id:1
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
   //don not need setter method caz we have already initialized the field using constructor

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }
}
