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
 * CancelSuscripcionPlan200Response
 */

@JsonTypeName("cancelSuscripcionPlan_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-11T16:08:12.066311+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class CancelSuscripcionPlan200Response {

  private String message;

  public CancelSuscripcionPlan200Response message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Mensaje de confirmación
   * @return message
   */
  
  @Schema(name = "message", example = "El plan de suscripción ha sido cancelado exitosamente.", description = "Mensaje de confirmación", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    CancelSuscripcionPlan200Response cancelSuscripcionPlan200Response = (CancelSuscripcionPlan200Response) o;
    return Objects.equals(this.message, cancelSuscripcionPlan200Response.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CancelSuscripcionPlan200Response {\n");
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

