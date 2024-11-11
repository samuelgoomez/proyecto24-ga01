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
 * Film
 */

@JsonTypeName("film")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-11T16:08:26.906146+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class Film {

  private Integer filmID;

  private String title;

  private Integer genreID;

  private Integer releaseYear;

  private Integer duration;

  private String description;

  private String photoURL;

  @Valid
  private List<Integer> arrayActors = new ArrayList<>();

  public Film() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Film(Integer filmID, String title, Integer genreID, Integer releaseYear, Integer duration, String description, String photoURL, List<Integer> arrayActors) {
    this.filmID = filmID;
    this.title = title;
    this.genreID = genreID;
    this.releaseYear = releaseYear;
    this.duration = duration;
    this.description = description;
    this.photoURL = photoURL;
    this.arrayActors = arrayActors;
  }

  public Film filmID(Integer filmID) {
    this.filmID = filmID;
    return this;
  }

  /**
   * Get filmID
   * @return filmID
   */
  @NotNull 
  @Schema(name = "filmID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("filmID")
  public Integer getFilmID() {
    return filmID;
  }

  public void setFilmID(Integer filmID) {
    this.filmID = filmID;
  }

  public Film title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  @NotNull 
  @Schema(name = "title", example = "Film Title", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Film genreID(Integer genreID) {
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

  public Film releaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
    return this;
  }

  /**
   * Get releaseYear
   * @return releaseYear
   */
  @NotNull 
  @Schema(name = "releaseYear", example = "2023", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("releaseYear")
  public Integer getReleaseYear() {
    return releaseYear;
  }

  public void setReleaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
  }

  public Film duration(Integer duration) {
    this.duration = duration;
    return this;
  }

  /**
   * Get duration
   * @return duration
   */
  @NotNull 
  @Schema(name = "duration", example = "120", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("duration")
  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public Film description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  @NotNull 
  @Schema(name = "description", example = "Film Sinopsis", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Film photoURL(String photoURL) {
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

  public Film arrayActors(List<Integer> arrayActors) {
    this.arrayActors = arrayActors;
    return this;
  }

  public Film addArrayActorsItem(Integer arrayActorsItem) {
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
    Film film = (Film) o;
    return Objects.equals(this.filmID, film.filmID) &&
        Objects.equals(this.title, film.title) &&
        Objects.equals(this.genreID, film.genreID) &&
        Objects.equals(this.releaseYear, film.releaseYear) &&
        Objects.equals(this.duration, film.duration) &&
        Objects.equals(this.description, film.description) &&
        Objects.equals(this.photoURL, film.photoURL) &&
        Objects.equals(this.arrayActors, film.arrayActors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filmID, title, genreID, releaseYear, duration, description, photoURL, arrayActors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Film {\n");
    sb.append("    filmID: ").append(toIndentedString(filmID)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    genreID: ").append(toIndentedString(genreID)).append("\n");
    sb.append("    releaseYear: ").append(toIndentedString(releaseYear)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
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

