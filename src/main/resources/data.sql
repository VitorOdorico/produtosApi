create table produto(
    id varchar(255) not null primary key,
    nome varchar(100) not null ,
    descricao varchar(300),
    preco numeric(18, 2),
    quantidade int not null
);