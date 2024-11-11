package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * FilmList
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-11T16:08:12.066311+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class FilmList {

  private Integer filmID;

  private String title;

  public FilmList filmID(Integer filmID) {
    this.filmID = filmID;
    return this;
  }

  /**
   * ID de la pelicula
   * @return filmID
   */
  
  @Schema(name = "filmID", example = "1", description = "ID de la pelicula", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("filmID")
  public Integer getFilmID() {
    return filmID;
  }

  public void setFilmID(Integer filmID) {
    this.filmID = filmID;
  }

  public FilmList title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Nombre de la pelicula
   * @return title
   */
  
  @Schema(name = "title", example = "El Señor de los Anillos", description = "Nombre de la pelicula", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FilmList filmList = (FilmList) o;
    return Objects.equals(this.filmID, filmList.filmID) &&
        Objects.equals(this.title, filmList.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filmID, title);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FilmList {\n");
    sb.append("    filmID: ").append(toIndentedString(filmID)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

