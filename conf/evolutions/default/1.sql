# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "JOGOS" ("id" BIGSERIAL PRIMARY KEY,"titulo" VARCHAR(254) NOT NULL,"capa" VARCHAR(254) NOT NULL,"disponibilidade" BOOLEAN NOT NULL);

# --- !Downs

drop table "JOGOS";

