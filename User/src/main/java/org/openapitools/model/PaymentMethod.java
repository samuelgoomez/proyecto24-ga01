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
 * PaymentMethod
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-12T19:10:30.312062600+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class PaymentMethod {

  private Integer paymentID;

  private Integer userID;

  private Integer cardNumber;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate expirationDate;

  private String cardHolderName;

  public PaymentMethod() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PaymentMethod(Integer paymentID, Integer userID, Integer cardNumber, LocalDate expirationDate, String cardHolderName) {
    this.paymentID = paymentID;
    this.userID = userID;
    this.cardNumber = cardNumber;
    this.expirationDate = expirationDate;
    this.cardHolderName = cardHolderName;
  }

  public PaymentMethod paymentID(Integer paymentID) {
    this.paymentID = paymentID;
    return this;
  }

  /**
   * ID único del método de pago
   * @return paymentID
   */
  @NotNull 
  @Schema(name = "paymentID", example = "12345", description = "ID único del método de pago", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paymentID")
  public Integer getPaymentID() {
    return paymentID;
  }

  public void setPaymentID(Integer paymentID) {
    this.paymentID = paymentID;
  }

  public PaymentMethod userID(Integer userID) {
    this.userID = userID;
    return this;
  }

  /**
   * ID de usuario
   * @return userID
   */
  @NotNull 
  @Schema(name = "userID", example = "12345", description = "ID de usuario", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("userID")
  public Integer getUserID() {
    return userID;
  }

  public void setUserID(Integer userID) {
    this.userID = userID;
  }

  public PaymentMethod cardNumber(Integer cardNumber) {
    this.cardNumber = cardNumber;
    return this;
  }

  /**
   * Número de la tarjeta de crédito/débito
   * @return cardNumber
   */
  @NotNull 
  @Schema(name = "cardNumber", example = "4111111111111111", description = "Número de la tarjeta de crédito/débito", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("cardNumber")
  public Integer getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(Integer cardNumber) {
    this.cardNumber = cardNumber;
  }

  public PaymentMethod expirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

  /**
   * Fecha de expiración de la tarjeta en formato YYYY-MM
   * @return expirationDate
   */
  @NotNull @Valid 
  @Schema(name = "expirationDate", description = "Fecha de expiración de la tarjeta en formato YYYY-MM", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("expirationDate")
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public PaymentMethod cardHolderName(String cardHolderName) {
    this.cardHolderName = cardHolderName;
    return this;
  }

  /**
   * Nombre del titular de la tarjeta
   * @return cardHolderName
   */
  @NotNull 
  @Schema(name = "cardHolderName", example = "Juan Pérez", description = "Nombre del titular de la tarjeta", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("cardHolderName")
  public String getCardHolderName() {
    return cardHolderName;
  }

  public void setCardHolderName(String cardHolderName) {
    this.cardHolderName = cardHolderName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentMethod paymentMethod = (PaymentMethod) o;
    return Objects.equals(this.paymentID, paymentMethod.paymentID) &&
        Objects.equals(this.userID, paymentMethod.userID) &&
        Objects.equals(this.cardNumber, paymentMethod.cardNumber) &&
        Objects.equals(this.expirationDate, paymentMethod.expirationDate) &&
        Objects.equals(this.cardHolderName, paymentMethod.cardHolderName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentID, userID, cardNumber, expirationDate, cardHolderName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentMethod {\n");
    sb.append("    paymentID: ").append(toIndentedString(paymentID)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    cardNumber: ").append(toIndentedString(cardNumber)).append("\n");
    sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
    sb.append("    cardHolderName: ").append(toIndentedString(cardHolderName)).append("\n");
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

