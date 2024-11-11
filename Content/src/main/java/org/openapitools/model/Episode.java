package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Episode
 */

@JsonTypeName("episode")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-11T16:08:26.906146+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class Episode {

  private Integer episodeID;

  private Integer serieID;

  private Integer numEpisodio;

  private Integer numTemporada;

  private String titulo;

  private String photoURL;

  public Episode() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Episode(Integer episodeID, Integer serieID, Integer numEpisodio, Integer numTemporada, String titulo, String photoURL) {
    this.episodeID = episodeID;
    this.serieID = serieID;
    this.numEpisodio = numEpisodio;
    this.numTemporada = numTemporada;
    this.titulo = titulo;
    this.photoURL = photoURL;
  }

  public Episode episodeID(Integer episodeID) {
    this.episodeID = episodeID;
    return this;
  }

  /**
   * Get episodeID
   * @return episodeID
   */
  @NotNull 
  @Schema(name = "episodeID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("episodeID")
  public Integer getEpisodeID() {
    return episodeID;
  }

  public void setEpisodeID(Integer episodeID) {
    this.episodeID = episodeID;
  }

  public Episode serieID(Integer serieID) {
    this.serieID = serieID;
    return this;
  }

  /**
   * Get serieID
   * @return serieID
   */
  @NotNull 
  @Schema(name = "serieID", example = "101", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("serieID")
  public Integer getSerieID() {
    return serieID;
  }

  public void setSerieID(Integer serieID) {
    this.serieID = serieID;
  }

  public Episode numEpisodio(Integer numEpisodio) {
    this.numEpisodio = numEpisodio;
    return this;
  }

  /**
   * Get numEpisodio
   * @return numEpisodio
   */
  @NotNull 
  @Schema(name = "numEpisodio", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("numEpisodio")
  public Integer getNumEpisodio() {
    return numEpisodio;
  }

  public void setNumEpisodio(Integer numEpisodio) {
    this.numEpisodio = numEpisodio;
  }

  public Episode numTemporada(Integer numTemporada) {
    this.numTemporada = numTemporada;
    return this;
  }

  /**
   * Get numTemporada
   * @return numTemporada
   */
  @NotNull 
  @Schema(name = "numTemporada", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("numTemporada")
  public Integer getNumTemporada() {
    return numTemporada;
  }

  public void setNumTemporada(Integer numTemporada) {
    this.numTemporada = numTemporada;
  }

  public Episode titulo(String titulo) {
    this.titulo = titulo;
    return this;
  }

  /**
   * Get titulo
   * @return titulo
   */
  @NotNull 
  @Schema(name = "titulo", example = "Episode Title", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("titulo")
  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Episode photoURL(String photoURL) {
    this.photoURL = photoURL;
    return this;
  }

  /**
   * Get photoURL
   * @return photoURL
   */
  @NotNull 
  @Schema(name = "photoURL", example = "https://example.com/photo.jpg", requiredMode = Schema.RequiredMode.REQUIRED)
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
    Episode episode = (Episode) o;
    return Objects.equals(this.episodeID, episode.episodeID) &&
        Objects.equals(this.serieID, episode.serieID) &&
        Objects.equals(this.numEpisodio, episode.numEpisodio) &&
        Objects.equals(this.numTemporada, episode.numTemporada) &&
        Objects.equals(this.titulo, episode.titulo) &&
        Objects.equals(this.photoURL, episode.photoURL);
  }

  @Override
  public int hashCode() {
    return Objects.hash(episodeID, serieID, numEpisodio, numTemporada, titulo, photoURL);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Episode {\n");
    sb.append("    episodeID: ").append(toIndentedString(episodeID)).append("\n");
    sb.append("    serieID: ").append(toIndentedString(serieID)).append("\n");
    sb.append("    numEpisodio: ").append(toIndentedString(numEpisodio)).append("\n");
    sb.append("    numTemporada: ").append(toIndentedString(numTemporada)).append("\n");
    sb.append("    titulo: ").append(toIndentedString(titulo)).append("\n");
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

