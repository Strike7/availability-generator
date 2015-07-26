package controllers

import play.api.db.slick.DBAction
import play.api.mvc.Controller
import models._
import play.api.db.slick._
import play.api.Play.current

object Cover extends Controller {

  def cover(id: Long) = DBAction { implicit rs =>
    val jogo = Jogos.get(id)
    TemporaryRedirect("http://s3-sa-east-1.amazonaws.com/strike7-image/cover/" + jogo.capa )
  }
}
