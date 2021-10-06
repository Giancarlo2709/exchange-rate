package com.bcp.reto.exchange.rate.utils;


import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;

public class Util {

  private static final Charset charsetDefault = Charset.forName("UTF-8");

  public Util() {
    super();
  }

  public static String encodeBase64(String encode) {
    Base64 base64 = new Base64();
    return new String(base64.encode(encode.getBytes(charsetDefault)), charsetDefault);
  }

  public static String decodeBase64(String decode) {
    Base64 base64 = new Base64();
    return new String(base64.decode(decode.getBytes(charsetDefault)), charsetDefault);
  }
}
