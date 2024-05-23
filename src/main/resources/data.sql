create table users(
    id integer primary key auto_increment,
    nome varchar(20),
    cpf varchar(11)

);
create table endereco(
    id integer primary key auto_increment,
    cep varchar(9),
    rua varchar (255),
    numero varchar(5),
    user_id integer references users(id)
);