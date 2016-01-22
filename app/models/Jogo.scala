package models

import java.sql.Timestamp
import java.text.{SimpleDateFormat, DateFormat}
import java.util.Date

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Tag

object date {
  val df = DateTimeFormat.forPattern("DDMM")

  def capaData(d: DateTime) = {
    df.print(d);
  }
}
case class Jogo(id: Option[Long], titulo: String, capa: String,var data: DateTime,  var disponibilidade: Boolean) {

  def capaNome = if (disponibilidade) capa else "ua-" + capa
  def capaData = date.capaData(data)
}

class Jogos(tag: Tag) extends Table[Jogo](tag, "JOGOS") {
  implicit def DateTime2TimeStamp = MappedColumnType.base[DateTime, Timestamp]({
    dt => new Timestamp(dt.getMillis )
  }, {
    tt => new DateTime(tt)
  })

  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def titulo = column[String]("titulo", O.NotNull)
  def capa = column[String]("capa", O.NotNull)
  def data = column[DateTime]("data")
  def disponibilidade = column[Boolean]("disponibilidade", O.NotNull)

  override def * = (id, titulo, capa, data, disponibilidade) <> (Jogo.tupled, Jogo.unapply _)
}


object Jogos {
  val jogos = TableQuery[Jogos]

  def list(implicit s: Session) = {
    val query = jogos.list
    query toList
  }

  def get(id: Long)(implicit s: Session): Jogo = {
    jogos.filter( _.id === id ) first
  }

  def insert(jogo: Jogo)(implicit s: Session) {
    jogos.insert(jogo)
  }

  def update(id: Long, jogo: Jogo)(implicit s: Session): Boolean = {
    jogos.filter(_.id === id ).update(jogo) > 0
  }
}
