package br.com.alura.forum.config.validation.dtos;

/**
 * @autor Adriano Rabello 14/01/2021  4:19 AM
 */
public class ValidationErrorDto {

    private String field;
    private String error;


    public ValidationErrorDto(String field, String error){

        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }
}
