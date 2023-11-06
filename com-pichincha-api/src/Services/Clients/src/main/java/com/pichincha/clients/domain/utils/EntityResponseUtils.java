package com.pichincha.clients.domain.utils;

import java.util.ArrayList;
import java.util.List;

public class EntityResponseUtils {
  private EntityResponseUtils() {
  }

  public static List<String> getMessageError(Exception exception) {
    var errors = new ArrayList<String>();
    
    if (exception.getCause() == null) 
      return errors;
    
    errors.add(exception.getCause().getMessage());

    return errors;
  }
    
  public static String generateMsg(String msg, Object... args)
  {
      return String.format(msg, args);
  }
}