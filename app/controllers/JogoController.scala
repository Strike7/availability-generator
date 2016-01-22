package controllers

import java.util.Date

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.api.db.slick.DBAction
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.mvc.{Action, BodyParsers, Controller}
import models._
import play.api.db.slick._
import play.api.Play.current



object JogoController extends Controller {
  val dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd")
  val jodaDateReads = Reads[DateTime](js =>
    js.validate[String].map[DateTime](dtString =>
      dateFormat.parseDateTime(dtString)
    )
  )

  implicit val JogoWrites = new Writes[Jogo]{
    override def writes(jogo: Jogo): JsValue = Json.obj(
      "id" -> JsNumber(jogo.id.get),
      "titulo" -> JsString(jogo.titulo),
      "capa" -> JsString(jogo.capa),
      "data" -> JsString(dateFormat.print( jogo.data )),
      "disponibilidade" -> JsBoolean(jogo.disponibilidade)
    )
  }
  implicit val jogoReads : Reads[Jogo] =
    (
        (JsPath \ "id" ).readNullable[Long] and
        (JsPath \ "titulo" ).read[String] and
        (JsPath \ "capa" ).read[String] and
        (JsPath \ "data" ).read[DateTime](jodaDateReads ) and
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
        jogoBanco.data = jogo.data
        Jogos.update(id, jogoBanco)
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