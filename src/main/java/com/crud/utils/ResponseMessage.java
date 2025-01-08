package com.crud.utils;

public class ResponseMessage {
  private String mensagem;

  public ResponseMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }
}
