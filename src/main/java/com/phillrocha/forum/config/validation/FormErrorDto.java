package com.phillrocha.forum.config.validation;

public class FormErrorDto {
    private String campo;

    private String mensagem;

    public FormErrorDto(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
