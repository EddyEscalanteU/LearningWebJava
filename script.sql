CREATE TABLE colonia (
	colonia_id serial PRIMARY KEY,
	nombre_colonia VARCHAR (45) NOT NULL,
	codigo_postal INTEGER NOT NULL
);


INSERT INTO colonia(nombre_colonia, codigo_postal) VALUES ('valor 1', 1010);
INSERT INTO colonia(nombre_colonia, codigo_postal) VALUES ('valor 2', 2020);
INSERT INTO colonia(nombre_colonia, codigo_postal) VALUES ('valor 3', 3030);