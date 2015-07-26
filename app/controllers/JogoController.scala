package controllers

import play.api.db.slick.DBAction
import play.api.mvc.Controller
import models._
import play.api.db.slick._
import play.api.Play.current

object JogoController extends Controller {

    def todos = DBAction { implicit rs =>
      Ok(views.html.jogos(Jogos.list))
    }


  def insert = DBAction { implicit rs =>
    Jogos.insert(
      new Jogo(null, "teste", "dyinglight.png", true))
    Created
  }

}
