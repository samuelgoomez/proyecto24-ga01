package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Serie
 */

@JsonTypeName("serie")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-11T16:08:26.906146+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class Serie {

  private Integer serieID;

  private String title;

  private Integer seasons;

  private Integer releaseYear;

  private Integer genreID;

  private String description;

  private String photoURL;

  @Valid
  private List<Integer> arrayActors = new ArrayList<>();

  public Serie() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Serie(Integer serieID, String title, Integer seasons, Integer releaseYear, Integer genreID, String description, String photoURL, List<Integer> arrayActors) {
    this.serieID = serieID;
    this.title = title;
    this.seasons = seasons;
    this.releaseYear = releaseYear;
    this.genreID = genreID;
    this.description = description;
    this.photoURL = photoURL;
    this.arrayActors = arrayActors;
  }

  public Serie serieID(Integer serieID) {
    this.serieID = serieID;
    return this;
  }

  /**
   * Get serieID
   * @return serieID
   */
  @NotNull 
  @Schema(name = "serieID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("serieID")
  public Integer getSerieID() {
    return serieID;
  }

  public void setSerieID(Integer serieID) {
    this.serieID = serieID;
  }

  public Serie title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  @NotNull 
  @Schema(name = "title", example = "Series Title", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Serie seasons(Integer seasons) {
    this.seasons = seasons;
    return this;
  }

  /**
   * Get seasons
   * @return seasons
   */
  @NotNull 
  @Schema(name = "seasons", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("seasons")
  public Integer getSeasons() {
    return seasons;
  }

  public void setSeasons(Integer seasons) {
    this.seasons = seasons;
  }

  public Serie releaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
    return this;
  }

  /**
   * Get releaseYear
   * @return releaseYear
   */
  
  @Schema(name = "releaseYear", example = "2022", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("releaseYear")
  public Integer getReleaseYear() {
    return releaseYear;
  }

  public void setReleaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
  }

  public Serie genreID(Integer genreID) {
    this.genreID = genreID;
    return this;
  }

  /**
   * Get genreID
   * @return genreID
   */
  @NotNull 
  @Schema(name = "genreID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("genreID")
  public Integer getGenreID() {
    return genreID;
  }

  public void setGenreID(Integer genreID) {
    this.genreID = genreID;
  }

  public Serie description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  @NotNull 
  @Schema(name = "description", example = "Series Sinopsis", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Serie photoURL(String photoURL) {
    this.photoURL = photoURL;
    return this;
  }

  /**
   * Imagen descriptiva
   * @return photoURL
   */
  @NotNull 
  @Schema(name = "photoURL", description = "Imagen descriptiva", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("photoURL")
  public String getPhotoURL() {
    return photoURL;
  }

  public void setPhotoURL(String photoURL) {
    this.photoURL = photoURL;
  }

  public Serie arrayActors(List<Integer> arrayActors) {
    this.arrayActors = arrayActors;
    return this;
  }

  public Serie addArrayActorsItem(Integer arrayActorsItem) {
    if (this.arrayActors == null) {
      this.arrayActors = new ArrayList<>();
    }
    this.arrayActors.add(arrayActorsItem);
    return this;
  }

  /**
   * Get arrayActors
   * @return arrayActors
   */
  @NotNull 
  @Schema(name = "arrayActors", example = "[101,102,103]", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("arrayActors")
  public List<Integer> getArrayActors() {
    return arrayActors;
  }

  public void setArrayActors(List<Integer> arrayActors) {
    this.arrayActors = arrayActors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Serie serie = (Serie) o;
    return Objects.equals(this.serieID, serie.serieID) &&
        Objects.equals(this.title, serie.title) &&
        Objects.equals(this.seasons, serie.seasons) &&
        Objects.equals(this.releaseYear, serie.releaseYear) &&
        Objects.equals(this.genreID, serie.genreID) &&
        Objects.equals(this.description, serie.description) &&
        Objects.equals(this.photoURL, serie.photoURL) &&
        Objects.equals(this.arrayActors, serie.arrayActors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serieID, title, seasons, releaseYear, genreID, description, photoURL, arrayActors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Serie {\n");
    sb.append("    serieID: ").append(toIndentedString(serieID)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    seasons: ").append(toIndentedString(seasons)).append("\n");
    sb.append("    releaseYear: ").append(toIndentedString(releaseYear)).append("\n");
    sb.append("    genreID: ").append(toIndentedString(genreID)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    photoURL: ").append(toIndentedString(photoURL)).append("\n");
    sb.append("    arrayActors: ").append(toIndentedString(arrayActors)).append("\n");
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

