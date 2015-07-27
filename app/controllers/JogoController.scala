package controllers

import play.api.db.slick.DBAction
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.mvc.{Action, BodyParsers, Controller}
import models._
import play.api.db.slick._
import play.api.Play.current



object JogoController extends Controller {

    implicit val JogoWrites = new Writes[Jogo]{
      override def writes(jogo: Jogo): JsValue = Json.obj(
        "titulo" -> JsString(jogo.titulo),
        "capa" -> JsString(jogo.capa),
        "disponibilidade" -> JsBoolean(jogo.disponibilidade)
      )
    }
  implicit val jogoReads : Reads[Jogo] =
    (
        (JsPath \ "id" ).readNullable[Long] and
        (JsPath \ "titulo" ).read[String] and
        (JsPath \ "capa" ).read[String] and
          (JsPath \ "disponibilidade").read[Boolean]
      )(Jogo.apply _)

    def todos = DBAction { implicit rs =>
      Ok(Json.toJson(
        Jogos.list
        )).as("application/json")
    }

  def update( id :Long) = DBAction(BodyParsers.parse.json) { implicit rs =>
    val jogoResult = rs.body.validate[Jogo]

    jogoResult.fold(
      erros => {
        BadRequest
      },
      jogo => {
        val jogoBanco = Jogos.get(id)
        jogoBanco.disponibilidade = jogo.disponibilidade
        Ok(Json.toJson(jogoBanco))
      }
    )
  }

  def insert = DBAction(BodyParsers.parse.json) { implicit rs =>
    val jogoResult = rs.body.validate[Jogo]

    jogoResult.fold(
      erros => {
        BadRequest
      },
      jogo => {
        Jogos.insert(jogo)
        Created
      }
    )
  }
}