package com.omega.exceptions

case class JSONException(e: Exception) extends Exception(e.getMessage) {
}
