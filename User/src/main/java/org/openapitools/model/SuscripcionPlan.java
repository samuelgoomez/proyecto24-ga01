package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * SuscripcionPlan
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-12T21:17:46.398497900+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class SuscripcionPlan {

  private Integer planID;

  private Integer userID;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate startDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate endDate;

  private Integer paymentID;

  public SuscripcionPlan() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SuscripcionPlan(Integer planID, Integer userID, LocalDate startDate, LocalDate endDate, Integer paymentID) {
    this.planID = planID;
    this.userID = userID;
    this.startDate = startDate;
    this.endDate = endDate;
    this.paymentID = paymentID;
  }

  public SuscripcionPlan planID(Integer planID) {
    this.planID = planID;
    return this;
  }

  /**
   * ID único del plan de suscripción
   * @return planID
   */
  @NotNull 
  @Schema(name = "planID", example = "5", description = "ID único del plan de suscripción", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("planID")
  public Integer getPlanID() {
    return planID;
  }

  public void setPlanID(Integer planID) {
    this.planID = planID;
  }

  public SuscripcionPlan userID(Integer userID) {
    this.userID = userID;
    return this;
  }

  /**
   * ID del usuario que contrata el plan
   * @return userID
   */
  @NotNull 
  @Schema(name = "userID", example = "1", description = "ID del usuario que contrata el plan", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("userID")
  public Integer getUserID() {
    return userID;
  }

  public void setUserID(Integer userID) {
    this.userID = userID;
  }

  public SuscripcionPlan startDate(LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Fecha de inicio del plan de suscripción
   * @return startDate
   */
  @NotNull @Valid 
  @Schema(name = "startDate", example = "Mon Jan 01 01:00:00 CET 2024", description = "Fecha de inicio del plan de suscripción", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("startDate")
  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public SuscripcionPlan endDate(LocalDate endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Fecha de finalización del plan de suscripción
   * @return endDate
   */
  @NotNull @Valid 
  @Schema(name = "endDate", example = "Tue Dec 31 01:00:00 CET 2024", description = "Fecha de finalización del plan de suscripción", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("endDate")
  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public SuscripcionPlan paymentID(Integer paymentID) {
    this.paymentID = paymentID;
    return this;
  }

  /**
   * Método de pago utilizado para contratar el plan
   * @return paymentID
   */
  @NotNull 
  @Schema(name = "paymentID", example = "1", description = "Método de pago utilizado para contratar el plan", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paymentID")
  public Integer getPaymentID() {
    return paymentID;
  }

  public void setPaymentID(Integer paymentID) {
    this.paymentID = paymentID;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SuscripcionPlan suscripcionPlan = (SuscripcionPlan) o;
    return Objects.equals(this.planID, suscripcionPlan.planID) &&
        Objects.equals(this.userID, suscripcionPlan.userID) &&
        Objects.equals(this.startDate, suscripcionPlan.startDate) &&
        Objects.equals(this.endDate, suscripcionPlan.endDate) &&
        Objects.equals(this.paymentID, suscripcionPlan.paymentID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(planID, userID, startDate, endDate, paymentID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SuscripcionPlan {\n");
    sb.append("    planID: ").append(toIndentedString(planID)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    paymentID: ").append(toIndentedString(paymentID)).append("\n");
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

