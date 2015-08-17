# --- !Ups
CREATE TABLE "JOGOS"
(
  "ID" bigint NOT NULL,
  "TITULO" text,
  "CAPA" text,
  "DISPONIBILIDADE" boolean,
  "CREATED_ON" timestamp without time zone,
  CONSTRAINT jogos_id_pk PRIMARY KEY ("ID")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "JOGOS"
  OWNER TO ag;

# --- !Downs

drop table "JOGOS";

