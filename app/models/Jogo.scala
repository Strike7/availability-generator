package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Tag

case class Jogo(id: Option[Long], titulo: String, capa: String, var disponibilidade: Boolean)

class Jogos(tag: Tag) extends Table[Jogo](tag, "JOGOS") {
  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def titulo = column[String]("titulo", O.NotNull)
  def capa = column[String]("capa", O.NotNull)
  def disponibilidade = column[Boolean]("disponibilidade", O.NotNull)

  override def * = (id, titulo, capa, disponibilidade) <> (Jogo.tupled, Jogo.unapply _)
}


object Jogos {
  val jogos = TableQuery[Jogos]

  def list(implicit s: Session) = {
    val query = jogos.list
    query.toList
  }

  def get(id: Long)(implicit s: Session): Jogo = {
    val query = jogos.filter(_.id === id)
    jogos.first
  }
  def insert(jogo: Jogo)(implicit s: Session) {
    jogos.insert(jogo)
  }
}
