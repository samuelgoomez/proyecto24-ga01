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
 * SeriesList
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-12T19:10:30.312062600+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class SeriesList {

  private Integer serieID;

  private String title;

  public SeriesList serieID(Integer serieID) {
    this.serieID = serieID;
    return this;
  }
  
  public SeriesList (Integer serieID, String title) {
	  this.serieID = serieID;
	  this.title = title;
  }

  /**
   * ID de serie
   * @return serieID
   */
  
  @Schema(name = "serieID", example = "1", description = "ID de serie", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("serieID")
  public Integer getSerieID() {
    return serieID;
  }

  public void setSerieID(Integer serieID) {
    this.serieID = serieID;
  }

  public SeriesList title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Nombre de la serie
   * @return title
   */
  
  @Schema(name = "title", example = "El Señor de los Anillos", description = "Nombre de la serie", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    SeriesList seriesList = (SeriesList) o;
    return Objects.equals(this.serieID, seriesList.serieID) &&
        Objects.equals(this.title, seriesList.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serieID, title);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SeriesList {\n");
    sb.append("    serieID: ").append(toIndentedString(serieID)).append("\n");
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

