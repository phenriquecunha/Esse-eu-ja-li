package learning.eejl.dtos;

import lombok.Data;

public class FieldFilter {
  public static final String UNIQUE = "id,volumeInfo(title,subtitle,authors,publisher,description,pageCount,categories,imageLinks(thumbnail))";
  public final static String LIST = "items(id,volumeInfo(title,subtitle,authors,publisher,description,pageCount,categories,imageLinks(thumbnail)))";
  public final static String CATEGORIES = "volumeInfo(categories)";
  public final static String PAGES = "volumeInfo(pageCount)";
}
