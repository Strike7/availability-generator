package controllers

import play.api._
import play.api.mvc._
import play.api.cache.Cache
import play.api.Play.current

import play.api.db._

object Cover extends Controller {

  def cover(id: String) = Action {
    TemporaryRedirect("http://s3-sa-east-1.amazonaws.com/strike7-image/cover/dyinglight.png")
  }
}
