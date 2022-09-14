/*
 * Copyright (C) 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.cloud.teleport.v2.templates.session;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Session object to store mapping information as per the session file generated by HarbourBridge.
 */
public class Session implements Serializable {
  /** Maps the Spanner table name to the synthetic PK. */
  private HashMap<String, SyntheticPKey> syntheticPKeys;

  /** Maps the source table name to the Spanner table name and column details. */
  private HashMap<String, NameAndCols> toSpanner;

  /** Denotes whether the session file is empty or not. */
  private boolean empty;

  public Session() {
    this.syntheticPKeys = new HashMap<String, SyntheticPKey>();
    this.toSpanner = new HashMap<String, NameAndCols>();
    this.empty = true;
  }

  public Session(
      HashMap<String, SyntheticPKey> syntheticPKeys, HashMap<String, NameAndCols> toSpanner) {
    this.syntheticPKeys =
        (syntheticPKeys == null) ? (new HashMap<String, SyntheticPKey>()) : syntheticPKeys;
    this.toSpanner = (toSpanner == null) ? (new HashMap<String, NameAndCols>()) : toSpanner;
    // toSpanner can never be null in a valid session file.
    this.empty = (toSpanner == null);
  }

  public HashMap<String, SyntheticPKey> getSyntheticPks() {
    return syntheticPKeys;
  }

  public HashMap<String, NameAndCols> getToSpanner() {
    return toSpanner;
  }

  public boolean isEmpty() {
    return empty;
  }

  public void setEmpty(boolean empty) {
    this.empty = empty;
  }

  public String toString() {
    return String.format(
        "{ 'syntheticPKeys': '%s', 'toSpanner': '%s' }", syntheticPKeys, toSpanner);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Session)) {
      return false;
    }
    final Session other = (Session) o;
    return this.empty == other.empty
        && this.syntheticPKeys.equals(other.syntheticPKeys)
        && this.toSpanner.equals(other.toSpanner);
  }
}