package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Tag

case class Jogo(id: Option[Long], titulo: String, capa: String, disponivel: Boolean)

class Jogos(tag: Tag) extends Table[Jogo](tag, "JOGOS") {
  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def titulo = column[String]("titulo", O.NotNull)
  def capa = column[String]("capa", O.NotNull)
  def disponivel = column[Boolean]("disponivel", O.NotNull)

  override def * = (id, titulo, capa, disponivel) <> (Jogo.tupled, Jogo.unapply _)
}


object Jogos {
  val jogos = TableQuery[Jogos]

  def list(implicit s: Session) = {
    val query = jogos.list
    query.toList
  }

  def insert(jogo: Jogo)(implicit s: Session) {
    jogos.insert(jogo)
  }

}
