package controllers

import play.api.db.slick.DBAction
import play.api.libs.json._
import play.api.mvc.Controller
import models._
import play.api.db.slick._
import play.api.Play.current



object JogoController extends Controller {

    implicit val JogoWrites = new Writes[Jogo]{
      override def writes(jogo: Jogo): JsValue = Json.obj(
        "titulo" -> JsString(jogo.titulo),
        "capa" -> JsString(jogo.capa),
        "disponivel" -> JsBoolean(jogo.disponivel)
      )
    }

    def todos = DBAction { implicit rs =>
      Ok(Json.toJson(
        Jogos.list
        )).as("application/json")
    }


  def insert = DBAction { implicit rs =>
    Jogos.insert(
      new Jogo(null, "teste", "dyinglight.png", true))
    Created
  }
}