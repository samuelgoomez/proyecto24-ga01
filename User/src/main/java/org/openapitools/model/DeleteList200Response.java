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
 * DeleteList200Response
 */

@JsonTypeName("deleteList_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-12T19:10:30.312062600+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class DeleteList200Response {

  private String message;

  public DeleteList200Response message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  
  @Schema(name = "message", example = "Lista eliminada exitosamente.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeleteList200Response deleteList200Response = (DeleteList200Response) o;
    return Objects.equals(this.message, deleteList200Response.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeleteList200Response {\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

