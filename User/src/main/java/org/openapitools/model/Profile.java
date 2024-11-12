package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.net.URI;
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
 * Profile
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-12T19:10:30.312062600+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class Profile {

  private Integer profileID;

  private Integer userID;

  private String name;

  /**
   * Tipo de perfil. Puede ser \"adult\" o \"child\".
   */
  public enum TypeEnum {
    ADULT("adult"),
    
    CHILD("child");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private TypeEnum type;

  private URI avatarURL;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  public Profile() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Profile(Integer profileID, Integer userID, String name, TypeEnum type, URI avatarURL, OffsetDateTime createdAt) {
    this.profileID = profileID;
    this.userID = userID;
    this.name = name;
    this.type = type;
    this.avatarURL = avatarURL;
    this.createdAt = createdAt;
  }

  public Profile profileID(Integer profileID) {
    this.profileID = profileID;
    return this;
  }

  /**
   * ID único del perfil
   * @return profileID
   */
  @NotNull 
  @Schema(name = "profileID", example = "2", description = "ID único del perfil", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("profileID")
  public Integer getProfileID() {
    return profileID;
  }

  public void setProfileID(Integer profileID) {
    this.profileID = profileID;
  }

  public Profile userID(Integer userID) {
    this.userID = userID;
    return this;
  }

  /**
   * ID usuario
   * @return userID
   */
  @NotNull 
  @Schema(name = "userID", example = "2", description = "ID usuario", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("userID")
  public Integer getUserID() {
    return userID;
  }

  public void setUserID(Integer userID) {
    this.userID = userID;
  }

  public Profile name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Nombre del perfil
   * @return name
   */
  @NotNull 
  @Schema(name = "name", example = "Perfil Principal", description = "Nombre del perfil", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Profile type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Tipo de perfil. Puede ser \"adult\" o \"child\".
   * @return type
   */
  @NotNull 
  @Schema(name = "type", example = "adult", description = "Tipo de perfil. Puede ser \"adult\" o \"child\".", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public Profile avatarURL(URI avatarURL) {
    this.avatarURL = avatarURL;
    return this;
  }

  /**
   * URL de la imagen de avatar del perfil
   * @return avatarURL
   */
  @NotNull @Valid 
  @Schema(name = "avatarURL", example = "https://example.com/avatars/avatar1.png", description = "URL de la imagen de avatar del perfil", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("avatarURL")
  public URI getAvatarURL() {
    return avatarURL;
  }

  public void setAvatarURL(URI avatarURL) {
    this.avatarURL = avatarURL;
  }

  public Profile createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Fecha y hora en que se creó el perfil
   * @return createdAt
   */
  @NotNull @Valid 
  @Schema(name = "createdAt", example = "2023-10-09T10:20:30Z", description = "Fecha y hora en que se creó el perfil", requiredMode = Schema.RequiredMode.REQUIRED)
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
    Profile profile = (Profile) o;
    return Objects.equals(this.profileID, profile.profileID) &&
        Objects.equals(this.userID, profile.userID) &&
        Objects.equals(this.name, profile.name) &&
        Objects.equals(this.type, profile.type) &&
        Objects.equals(this.avatarURL, profile.avatarURL) &&
        Objects.equals(this.createdAt, profile.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(profileID, userID, name, type, avatarURL, createdAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Profile {\n");
    sb.append("    profileID: ").append(toIndentedString(profileID)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    avatarURL: ").append(toIndentedString(avatarURL)).append("\n");
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

