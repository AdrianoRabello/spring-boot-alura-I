package br.com.alura.forum.dtos;

/**
 * @autor Adriano Rabello 16/01/2021  4:14 PM
 */
public class TokenDto {

    private String token;
    private String bearer;

    public TokenDto(String token, String bearer) {
        this.token= token;
        this.bearer = bearer;
    }


    public String getToken() {
        return token;
    }

    public String getBearer() {
        return bearer;
    }
}
