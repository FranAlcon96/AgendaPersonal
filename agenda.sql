create table usuario( 
usuario varchar(25) not null, 
passwd varchar(25) not null, 
primary key (usuario)
)

create table contacto( 
id integer unsigned not null auto_increment, 
nombre varchar(50) not null, 
apellido varchar(50) not null, 
direccion varchar(50) not null,
tlf integer(9) not null,
descripcion varchar(150) not null,
usuario varchar(25) not null, 
primary key (id),
CONSTRAINT FK_usuario FOREIGN KEY (usuario)
    REFERENCES usuario(usuario)
)auto_increment=1;
