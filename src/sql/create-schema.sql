create table klasse (id bigserial not null, class_name varchar(255) unique, primary key (id));
create table maturafach (note integer not null, id bigserial not null, schueler_id bigint, fach varchar(255), primary key (id));
create table schueler (id bigserial not null, klasse_id bigint, name varchar(255), primary key (id));
alter table if exists maturafach add constraint FKs3436u39eawgm4ex0xv6xn8gv foreign key (schueler_id) references schueler;
alter table if exists schueler add constraint FKox1rqxdb1y7c56lgc11896dd2 foreign key (klasse_id) references klasse;
