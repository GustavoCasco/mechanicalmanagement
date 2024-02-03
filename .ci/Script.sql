drop table if exists tbhg_agendamentos;
drop table if exists tbhg_horarios;
drop table if exists tbhg_servicos;
drop table if exists tbhg_usuario;
drop table if exists tbhg_preco;
drop table if exists tbhg_tipo_usuario;
drop table if exists tbhg_acesso;

create table tbhg_acesso (
id_acesso SERIAL primary key not null,
email varchar(255) not null unique,
palavra_chave varchar(255) not null,
numero_documento bigint not null
);

create table tbhg_tipo_usuario (
id_tipousuario SERIAL primary key not null,
descricao_tipo_usuario varchar(255)
);

create table tbhg_preco (
id_preco SERIAL primary key not null,
preco numeric(7,2) not null
);

create table tbhg_usuario (
id_usuario SERIAL primary key not null,
nome varchar(255) not null,
celular bigint,
id_acesso bigint not null,
id_tipousuario bigint not null,
constraint fk_acesso foreign key (id_acesso) references tbhg_acesso(id_acesso),
constraint fk_tipousuario foreign key (id_tipousuario) references tbhg_tipo_usuario(id_tipousuario)
);

create table tbhg_servicos (
id_servicos SERIAL primary key not null,
servico varchar(255) not null,
descricaoservico varchar(255) not null,
id_preco bigint not null,
id_usuario bigint not null,
constraint fk_usuario foreign key (id_usuario) references tbhg_usuario(id_usuario),
constraint fk_precos foreign key (id_preco) references tbhg_preco(id_preco)
);

create table tbhg_horarios (
id_horario SERIAL primary key not null,
horario TIME not null,
tempoatendimento int not null,
id_servicos bigint not null,
constraint fk_servicos foreign key (id_servicos) references tbhg_servicos(id_servicos)
);

create table tbhg_agendamentos (
id_agendamentos SERIAL primary key not null,
dataAgendamento date default current_date,
id_usuario bigint not null,
id_servicos bigint not null,
id_horario bigint not null,
constraint fk_usuario foreign key (id_usuario) references tbhg_usuario(id_usuario),
constraint fk_horario foreign key (id_horario) references tbhg_horarios(id_horario),
constraint fk_servicos foreign key (id_servicos) references tbhg_servicos(id_servicos)
);

insert into tbhg_tipo_usuario values (1, 'COMUM'), (2, 'ADM'), (3, 'FUNCIONARIO');
