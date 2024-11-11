package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Actor
 */

@JsonTypeName("actor")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-11T16:08:26.906146+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class Actor {

  private Integer actorID;

  private String name;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime birthdayDate;

  private String photoURL;

  public Actor() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Actor(Integer actorID, String name, OffsetDateTime birthdayDate, String photoURL) {
    this.actorID = actorID;
    this.name = name;
    this.birthdayDate = birthdayDate;
    this.photoURL = photoURL;
  }

  public Actor actorID(Integer actorID) {
    this.actorID = actorID;
    return this;
  }

  /**
   * Get actorID
   * @return actorID
   */
  @NotNull 
  @Schema(name = "actorID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("actorID")
  public Integer getActorID() {
    return actorID;
  }

  public void setActorID(Integer actorID) {
    this.actorID = actorID;
  }

  public Actor name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull 
  @Schema(name = "name", example = "Actor Name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Actor birthdayDate(OffsetDateTime birthdayDate) {
    this.birthdayDate = birthdayDate;
    return this;
  }

  /**
   * Get birthdayDate
   * @return birthdayDate
   */
  @NotNull @Valid 
  @Schema(name = "birthdayDate", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("birthdayDate")
  public OffsetDateTime getBirthdayDate() {
    return birthdayDate;
  }

  public void setBirthdayDate(OffsetDateTime birthdayDate) {
    this.birthdayDate = birthdayDate;
  }

  public Actor photoURL(String photoURL) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Actor actor = (Actor) o;
    return Objects.equals(this.actorID, actor.actorID) &&
        Objects.equals(this.name, actor.name) &&
        Objects.equals(this.birthdayDate, actor.birthdayDate) &&
        Objects.equals(this.photoURL, actor.photoURL);
  }

  @Override
  public int hashCode() {
    return Objects.hash(actorID, name, birthdayDate, photoURL);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Actor {\n");
    sb.append("    actorID: ").append(toIndentedString(actorID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    birthdayDate: ").append(toIndentedString(birthdayDate)).append("\n");
    sb.append("    photoURL: ").append(toIndentedString(photoURL)).append("\n");
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

