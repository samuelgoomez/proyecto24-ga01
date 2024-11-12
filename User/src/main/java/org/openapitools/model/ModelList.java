package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ModelList
 */

@JsonTypeName("List")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-12T19:10:30.312062600+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class ModelList {

  private Integer listID;

  private Integer userID;

  private String name;

  @Valid
  private List<Integer> films = new ArrayList<>();

  @Valid
  private List<Integer> series = new ArrayList<>();

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  public ModelList() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ModelList(Integer listID, Integer userID, String name, List<Integer> films, List<Integer> series, OffsetDateTime createdAt) {
    this.listID = listID;
    this.userID = userID;
    this.name = name;
    this.films = films;
    this.series = series;
    this.createdAt = createdAt;
  }

  public ModelList listID(Integer listID) {
    this.listID = listID;
    return this;
  }

  /**
   * Identificador único de la lista
   * @return listID
   */
  @NotNull 
  @Schema(name = "listID", example = "101", description = "Identificador único de la lista", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("listID")
  public Integer getListID() {
    return listID;
  }

  public void setListID(Integer listID) {
    this.listID = listID;
  }

  public ModelList userID(Integer userID) {
    this.userID = userID;
    return this;
  }

  /**
   * ID del usuario
   * @return userID
   */
  @NotNull 
  @Schema(name = "userID", example = "101", description = "ID del usuario", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("userID")
  public Integer getUserID() {
    return userID;
  }

  public void setUserID(Integer userID) {
    this.userID = userID;
  }

  public ModelList name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Nombre de la lista
   * @return name
   */
  @NotNull 
  @Schema(name = "name", example = "Mis Series Favoritas", description = "Nombre de la lista", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ModelList films(List<Integer> films) {
    this.films = films;
    return this;
  }

  public ModelList addFilmsItem(Integer filmsItem) {
    if (this.films == null) {
      this.films = new ArrayList<>();
    }
    this.films.add(filmsItem);
    return this;
  }

  /**
   * Peliculas contenidas en la lista
   * @return films
   */
  @NotNull 
  @Schema(name = "films", description = "Peliculas contenidas en la lista", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("films")
  public List<Integer> getFilms() {
    return films;
  }

  public void setFilms(List<Integer> films) {
    this.films = films;
  }

  public ModelList series(List<Integer> series) {
    this.series = series;
    return this;
  }

  public ModelList addSeriesItem(Integer seriesItem) {
    if (this.series == null) {
      this.series = new ArrayList<>();
    }
    this.series.add(seriesItem);
    return this;
  }

  /**
   * Series contenidas en la lista
   * @return series
   */
  @NotNull 
  @Schema(name = "series", description = "Series contenidas en la lista", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("series")
  public List<Integer> getSeries() {
    return series;
  }

  public void setSeries(List<Integer> series) {
    this.series = series;
  }

  public ModelList createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Fecha y hora de creación de la lista
   * @return createdAt
   */
  @NotNull @Valid 
  @Schema(name = "createdAt", example = "2023-10-09T10:00Z", description = "Fecha y hora de creación de la lista", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelList _list = (ModelList) o;
    return Objects.equals(this.listID, _list.listID) &&
        Objects.equals(this.userID, _list.userID) &&
        Objects.equals(this.name, _list.name) &&
        Objects.equals(this.films, _list.films) &&
        Objects.equals(this.series, _list.series) &&
        Objects.equals(this.createdAt, _list.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(listID, userID, name, films, series, createdAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelList {\n");
    sb.append("    listID: ").append(toIndentedString(listID)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    films: ").append(toIndentedString(films)).append("\n");
    sb.append("    series: ").append(toIndentedString(series)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
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

