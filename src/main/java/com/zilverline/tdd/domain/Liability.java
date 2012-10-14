package com.zilverline.tdd.domain;


/**
 * Liability.java
 *
 */
public class Liability {

  private String cause;

  public Liability() {
    super();
  }

  public Liability(String cause) {
    super();
    this.cause = cause;
  }

  public String getCause() {
    return cause;
  }

  public void setCause(String cause) {
    this.cause = cause;
  }
  
}
