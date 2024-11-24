CREATE DATABASE "Content";

\c Content

CREATE TABLE genres (
    genreID SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO genres ("name") VALUES
    ('Acción'),
    ('Comedia'),
    ('Drama'),
    ('Terror'),
    ('Misterio/Suspense'),
    ('Infantil');


CREATE TABLE films (
    filmID SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genreID INTEGER,
    releaseYear INTEGER, 
    duration INTEGER CHECK (duration > 0), 
    description TEXT,
    photoURL VARCHAR(500), 
    arrayActors INTEGER[],
	FOREIGN KEY (genreID) REFERENCES genres(genreID) ON DELETE SET NULL
);

INSERT INTO films (title, genreID, releaseYear, duration, description, photoURL, arrayActors) VALUES
('Inception', 1, 2010, 148, 'Un ladrón especializado en infiltrarse en los sueños intenta un último trabajo.', 'https://www.originalfilmart.com/cdn/shop/files/inception_2010_french_original_film_art_5000x.webp?v=1686692704', ARRAY[1, 2, 3]),
('John Wick', 1, 2014, 101, 'Un asesino retirado regresa por venganza.', 'https://es.web.img3.acsta.net/pictures/14/10/01/14/18/135831.jpg', ARRAY[4, 5, 6]),
('Superbad', 2, 2007, 113, 'Dos amigos buscan una última gran noche antes de la universidad.', 'https://i.pinimg.com/originals/4b/49/b6/4b49b66956c004409f5dfc323faf432f.jpg', ARRAY[7, 8, 9]),
('The Hangover', 2, 2009, 100, 'Una despedida de soltero en Las Vegas que se sale de control.', 'https://img.ecartelera.com/noticias/fotos/3700/3721/1.jpg', ARRAY[10, 11, 12]),
('Forrest Gump', 3, 1994, 142, 'La increíble historia de un hombre que deja su huella en la historia.', 'https://es.web.img3.acsta.net/pictures/bzp/01/10568.jpg', ARRAY[13, 14, 15]),
('Vinicius', 3, 2024, 144, 'Documental sobre la estrella del Madrid, Vinicius Junior', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwWGpO0QcA7mLWHejKLWBK588gUx0Uu5CrTw&s', ARRAY[16, 17, 18]),
('Terrifier', 4, 2016, 82, 'Un payaso asesino aterroriza a dos mujeres durante Halloween.', 'https://pics.filmaffinity.com/Terrifier-643530005-mmed.jpg', ARRAY[19, 20, 1]),
('It', 4, 2017, 135, 'Un grupo de niños enfrenta a un terrorífico payaso.', 'https://pics.filmaffinity.com/It-787119144-large.jpg', ARRAY[2, 3, 4]),
('Se7en', 5, 1995, 127, 'Dos detectives persiguen a un asesino que sigue los pecados capitales.', 'https://pics.filmaffinity.com/seven_se7en-498520078-large.jpg', ARRAY[5, 6, 7]),
('Gone Girl', 5, 2014, 149, 'Una mujer desaparece y deja una compleja red de secretos.', 'https://revistacalibre38.com/wp-content/uploads/2014/10/perdida-cartel-pic33d.jpg', ARRAY[8, 9, 10]),
('Toy Story', 6, 1995, 81, 'Unos juguetes cobran vida cuando los humanos no están.', 'https://es.web.img3.acsta.net/pictures/14/03/17/10/20/509771.jpg', ARRAY[11, 12, 13]),
('Frozen', 6, 2013, 102, 'Dos hermanas luchan contra el hielo y el tiempo para salvar su reino.', 'https://images.cdn1.buscalibre.com/fit-in/360x360/92/56/9256b8e9e8b8aefb4b9664854d7f15bd.jpg', ARRAY[14, 15, 16]),
('The Dark Knight', 1, 2008, 152, 'Batman enfrenta al Joker en una épica batalla por Gotham.', 'https://fancueva.com/wp-content/uploads/2008/04/TheDarkKnightPoster.jpg', ARRAY[17, 18, 19]),
('Avengers Endgame', 1, 2019, 181, 'Los Vengadores deberán reunirse una vez más para intentar detener a Thanos y restaurar el orden en el universo de una vez por todas', 'https://hips.hearstapps.com/hmg-prod/images/poster-vengadores-endgame-1552567490.jpg', ARRAY[20, 1, 2]),
('The Godfather', 3, 1972, 175, 'La historia de una familia mafiosa y su lucha por el poder.', 'https://m.media-amazon.com/images/M/MV5BYTJkNGQyZDgtZDQ0NC00MDM0LWEzZWQtYzUzZDEwMDljZWNjXkEyXkFqcGc@._V1_.jpg', ARRAY[3, 4, 5]),
('A Quiet Place', 4, 2018, 90, 'Una familia debe vivir en silencio para evitar a criaturas letales.', 'https://pics.filmaffinity.com/Un_lugar_tranquilo-854286921-large.jpg', ARRAY[6, 7, 8]),
('Shutter Island', 5, 2010, 138, 'Un detective investiga un hospital psiquiátrico en una isla aislada.', 'https://es.web.img3.acsta.net/medias/nmedia/18/69/96/74/19239968.jpg', ARRAY[9, 10, 11]),
('The Lion King', 6, 1994, 88, 'Un joven león descubre su destino como rey de la sabana.', 'https://i.pinimg.com/736x/7c/d8/c5/7cd8c5910ee8aa20f4f371e06e16209d.jpg', ARRAY[12, 13, 14]),
('Saw', 4, 2004, 103, 'Adam y Lawrence se despiertan encadenados en un sucio baño con un cadáver entre ellos.', 'https://es.web.img3.acsta.net/medias/nmedia/18/89/75/36/20065254.jpg', ARRAY[15, 16, 17]),
('Inside Out', 6, 2015, 95, 'Las emociones de una niña toman vida y enfrentan desafíos.', 'https://static.wikia.nocookie.net/disney/images/6/6a/Inside_Out.png/revision/latest?cb=20141213080410&path-prefix=es', ARRAY[18, 19, 20]);

CREATE TABLE series (
    serieID SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    seasons INTEGER CHECK (seasons > 0),
    releaseYear INTEGER, 
    genreID INTEGER, 
    description TEXT,
    photoURL VARCHAR(500), 
    arrayActors INTEGER[],
	FOREIGN KEY (genreID) REFERENCES genres(genreID) ON DELETE SET NULL
);

INSERT INTO series (title, seasons, releaseYear, genreID, description, photoURL, arrayActors) VALUES
-- Acción (genreID: 1)
('El Juego del Calamar', 1, 2021, 1, 'Cientos de personas compiten en un mortal juego de supervivencia por un gran premio.', 'https://pics.filmaffinity.com/squid_game-688981365-mmed.jpg', ARRAY[1, 2, 3]),
('Vikings', 6, 2013, 1, 'La historia de Ragnar Lothbrok y sus conquistas como vikingo legendario.', 'https://static.posters.cz/image/750/posters/vikingos-key-art-i24091.jpg', ARRAY[4, 5, 6]),
('Peaky Blinders', 6, 2013, 1, 'La familia Shelby construye su imperio criminal en la Inglaterra de la posguerra.', 'https://www.ecartelera.com/carteles-series/200/271/004_m.jpg', ARRAY[7, 8, 9]),

-- Comedia (genreID: 2)
('The Office', 9, 2005, 2, 'Una comedia sobre la vida cotidiana en una oficina de ventas.', 'https://pics.filmaffinity.com/The_Office_Serie_de_TV-862602609-large.jpg', ARRAY[10, 11, 12]),
('Friends', 10, 1994, 2, 'Seis amigos navegan por la vida, el amor y el trabajo en Nueva York.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyH39137PpzaN7BvCrsgq7WvPmlLuPo4zbvQ&s', ARRAY[13, 14, 15]),
('La Que Se Avecina', 13, 2007, 2, 'La vida y los conflictos entre los vecinos de una peculiar comunidad de propietarios.', 'https://static.wikia.nocookie.net/miradordemontepinar/images/9/9b/La_que_se_avecina_Temporada_1.png/revision/latest?cb=20190802144108&path-prefix=es', ARRAY[16, 17, 18]),

-- Drama (genreID: 3)
('Breaking Bad', 5, 2008, 3, 'Un profesor de química se convierte en narcotraficante para asegurar el futuro de su familia.', 'https://es.web.img3.acsta.net/pictures/18/04/04/22/52/3191575.jpg', ARRAY[19, 20, 1]),
('The Crown', 5, 2016, 3, 'La vida de la Reina Isabel II y los eventos que definieron su reinado.', 'https://pics.filmaffinity.com/the_crown-838357032-large.jpg', ARRAY[2, 3, 4]),
('The Sopranos', 6, 1999, 3, 'La vida de un mafioso que equilibra su familia y el crimen organizado.', 'https://pics.filmaffinity.com/the_sopranos-196243243-mmed.jpg', ARRAY[5, 6, 7]),

-- Terror (genreID: 4)
('Stranger Things', 4, 2016, 4, 'Un grupo de amigos descubre misterios sobrenaturales en su pequeña ciudad.', 'https://static.posters.cz/image/750/posters/stranger-things-one-sheet-i63329.jpg', ARRAY[8, 9, 10]),
('The Walking Dead', 11, 2010, 4, 'Un grupo de sobrevivientes lucha por mantenerse con vida en un mundo lleno de zombis.', 'https://m.media-amazon.com/images/I/61g-Tch64uL.jpg', ARRAY[11, 12, 13]),
('From', 2, 2022, 4, 'Una misteriosa ciudad atrapa a los viajeros, enfrentándolos a sus miedos más oscuros.', 'https://preview.redd.it/56ir1sobabtd1.jpeg?auto=webp&s=c669c89009d339fd9bf99975efe38804b6e2eeda', ARRAY[14, 15, 16]),

-- Misterio/Suspense (genreID: 5)
('Sherlock', 4, 2010, 5, 'Una moderna adaptación del detective más famoso del mundo.', 'https://www.formulatv.com/images/series/posters/400/481/dest_1.jpg', ARRAY[17, 18, 19]),
('Dark', 3, 2017, 5, 'Un pequeño pueblo se ve envuelto en un misterio que abarca varias generaciones.', 'https://es.web.img3.acsta.net/pictures/17/11/10/12/27/3064798.jpg', ARRAY[20, 1, 2]),
('The Boys', 3, 2019, 5, 'Un grupo intenta exponer la corrupción detrás de los superhéroes.', 'https://sm.ign.com/ign_es/tv/t/the-boys/the-boys_474x.jpg', ARRAY[3, 4, 5]),
('Loki', 2, 2021, 5, 'El dios de las travesuras explora líneas temporales alternativas.', 'https://lumiere-a.akamaihd.net/v1/images/13_arch_teaserposter2_1sht_displus_spain_e527b99c.jpeg', ARRAY[6, 7, 8]),

-- Infantil (genreID: 6)
('Los Simpson', 35, 1989, 6, 'Una comedia animada sobre una familia peculiar en la ciudad de Springfield.', 'https://pics.filmaffinity.com/the_simpsons-397676780-mmed.jpg', ARRAY[9, 10, 11]),
('Adventure Time', 10, 2010, 6, 'Las aventuras de Finn y Jake en la Tierra de Ooo.', 'https://pics.filmaffinity.com/adventure_time_with_finn_jake-507797147-large.jpg', ARRAY[12, 13, 14]),
('El Increíble Mundo de Gumball', 6, 2011, 6, 'Un niño gato y su hermano pez enfrentan situaciones absurdas en su ciudad.', 'https://pics.filmaffinity.com/the_amazing_world_of_gumball-580406911-large.jpg', ARRAY[15, 16, 17]),
('Steven Universe', 5, 2013, 6, 'Un joven mitad humano y mitad gema protege el universo con sus amigos.', 'https://i.pinimg.com/736x/05/bf/b2/05bfb2a27f748a49ae1b286a693e62cd.jpg', ARRAY[18, 19, 20]);

CREATE TABLE episodes (
    episodeID SERIAL PRIMARY KEY,       
    serieID INTEGER,            
    numEpisodio INTEGER NOT NULL,        
    numTemporada INTEGER NOT NULL,       
    titulo VARCHAR(255) NOT NULL,         
    photoURL VARCHAR(255),
	FOREIGN KEY (serieID) REFERENCES series(serieID) ON DELETE CASCADE
);

INSERT INTO episodes (serieID, numEpisodio, numTemporada, titulo, photoURL) VALUES
-- El Juego del Calamar
(1, 1, 1, 'Luz Roja, Luz Verde', 'https://pics.filmaffinity.com/squid_game-688981365-mmed.jpg'),
(1, 2, 1, 'Infierno', 'https://pics.filmaffinity.com/squid_game-688981365-mmed.jpg'),

-- Vikings
(2, 1, 1, 'Ritos de Sangre', 'https://static.posters.cz/image/750/posters/vikingos-key-art-i24091.jpg'),
(2, 2, 1, 'La Ira de los Nórdicos', 'https://static.posters.cz/image/750/posters/vikingos-key-art-i24091.jpg'),

-- Peaky Blinders
(3, 1, 1, 'Esos Perros Negros', 'https://www.ecartelera.com/carteles-series/200/271/004_m.jpg'),
(3, 2, 1, 'La Ciudad de las Espadas', 'https://www.ecartelera.com/carteles-series/200/271/004_m.jpg'),

-- The Office
(4, 1, 1, 'Pilot', 'https://pics.filmaffinity.com/The_Office_Serie_de_TV-862602609-large.jpg'),
(4, 2, 1, 'Diversity Day', 'https://pics.filmaffinity.com/The_Office_Serie_de_TV-862602609-large.jpg'),

-- Friends
(5, 1, 1, 'El que lo empieza todo', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyH39137PpzaN7BvCrsgq7WvPmlLuPo4zbvQ&s'),
(5, 2, 1, 'El del Sofá', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyH39137PpzaN7BvCrsgq7WvPmlLuPo4zbvQ&s'),

-- La Que Se Avecina
(6, 1, 1, 'Una Comunidad Especial', 'https://static.wikia.nocookie.net/miradordemontepinar/images/9/9b/La_que_se_avecina_Temporada_1.png/revision/latest?cb=20190802144108&path-prefix=es'),
(6, 2, 1, 'Los Cuatro de Montepinar', 'https://static.wikia.nocookie.net/miradordemontepinar/images/9/9b/La_que_se_avecina_Temporada_1.png/revision/latest?cb=20190802144108&path-prefix=es'),

-- Breaking Bad
(7, 1, 1, 'Pilot', 'https://es.web.img3.acsta.net/pictures/18/04/04/22/52/3191575.jpg'),
(7, 2, 1, 'Cat´s in the Bag...', 'https://es.web.img3.acsta.net/pictures/18/04/04/22/52/3191575.jpg'),

-- The Crown
(8, 1, 1, 'Her Majesty', 'https://pics.filmaffinity.com/the_crown-838357032-large.jpg'),
(8, 2, 1, 'Windsor', 'https://pics.filmaffinity.com/the_crown-838357032-large.jpg'),

-- The Sopranos
(9, 1, 1, 'Piloto', 'https://pics.filmaffinity.com/the_sopranos-196243243-mmed.jpg'),
(9, 2, 1, '46 Long', 'https://pics.filmaffinity.com/the_sopranos-196243243-mmed.jpg'),

-- Stranger Things
(10, 1, 1, 'The Vanishing of Will Byers', 'https://static.posters.cz/image/750/posters/stranger-things-one-sheet-i63329.jpg'),
(10, 2, 1, 'The Weirdo on Maple Street', 'https://static.posters.cz/image/750/posters/stranger-things-one-sheet-i63329.jpg'),

-- The Walking Dead
(11, 1, 1, 'Days Gone Bye', 'https://m.media-amazon.com/images/I/61g-Tch64uL.jpg'),
(11, 2, 1, 'Guts', 'https://m.media-amazon.com/images/I/61g-Tch64uL.jpg'),

-- From
(12, 1, 1, 'Piloto', 'https://preview.redd.it/56ir1sobabtd1.jpeg?auto=webp&s=c669c89009d339fd9bf99975efe38804b6e2eeda'),
(12, 2, 1, 'Atrapados', 'https://preview.redd.it/56ir1sobabtd1.jpeg?auto=webp&s=c669c89009d339fd9bf99975efe38804b6e2eeda'),

-- Sherlock
(13, 1, 1, 'A Study in Pink', 'https://www.formulatv.com/images/series/posters/400/481/dest_1.jpg'),
(13, 2, 1, 'The Blind Banker', 'https://www.formulatv.com/images/series/posters/400/481/dest_1.jpg'),

-- Dark
(14, 1, 1, 'El Principio y el Fin', 'https://es.web.img3.acsta.net/pictures/17/11/10/12/27/3064798.jpg'),
(14, 2, 1, 'Mentiras', 'https://es.web.img3.acsta.net/pictures/17/11/10/12/27/3064798.jpg'),

-- The Boys
(15, 1, 1, 'The Name of the Game', 'https://sm.ign.com/ign_es/tv/t/the-boys/the-boys_474x.jpg'),
(15, 2, 1, 'Cherry', 'https://sm.ign.com/ign_es/tv/t/the-boys/the-boys_474x.jpg'),

-- Loki
(16, 1, 1, 'Glorious Purpose', 'https://lumiere-a.akamaihd.net/v1/images/13_arch_teaserposter2_1sht_displus_spain_e527b99c.jpeg'),
(16, 2, 1, 'The Variant', 'https://lumiere-a.akamaihd.net/v1/images/13_arch_teaserposter2_1sht_displus_spain_e527b99c.jpeg'),

-- Los Simpson
(17, 1, 1, 'Bart, el Genio', 'https://pics.filmaffinity.com/the_simpsons-397676780-mmed.jpg'),
(17, 2, 1, 'La Odisea de Homer', 'https://pics.filmaffinity.com/the_simpsons-397676780-mmed.jpg'),

-- Adventure Time
(18, 1, 1, 'Slumber Party Panic', 'https://pics.filmaffinity.com/adventure_time_with_finn_jake-507797147-large.jpg'),
(18, 2, 1, 'Trouble in Lumpy Space', 'https://pics.filmaffinity.com/adventure_time_with_finn_jake-507797147-large.jpg'),

-- El Increíble Mundo de Gumball
(19, 1, 1, 'El DVD', 'https://pics.filmaffinity.com/the_amazing_world_of_gumball-580406911-large.jpg'),
(19, 2, 1, 'El Beso', 'https://pics.filmaffinity.com/the_amazing_world_of_gumball-580406911-large.jpg'),

-- Steven Universe
(20, 1, 1, 'Gem Glow', 'https://i.pinimg.com/736x/05/bf/b2/05bfb2a27f748a49ae1b286a693e62cd.jpg'),
(20, 2, 1, 'Laser Light Cannon', 'https://i.pinimg.com/736x/05/bf/b2/05bfb2a27f748a49ae1b286a693e62cd.jpg');


CREATE TABLE actors (
    actorID SERIAL PRIMARY KEY,         -- Integer autoincremental para el ID del actor
    "name" VARCHAR(255) NOT NULL,          -- Nombre del actor, tipo String en Java
    birthdayDate DATE,                  -- Fecha de nacimiento, sin hora ni zona horaria
    photoURL VARCHAR(512)               -- URL de la foto, tipo String en Java
);

INSERT INTO actors ("name", birthdaydate, photoURL) VALUES
('Robert Downey Jr.', '1965-04-04', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Robert_Downey_Jr_2014_Comic_Con_%28cropped%29.jpg/640px-Robert_Downey_Jr_2014_Comic_Con_%28cropped%29.jpg'),
('Scarlett Johansson', '1984-11-22', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/60/Scarlett_Johansson_by_Gage_Skidmore_2_%28cropped%29.jpg/800px-Scarlett_Johansson_by_Gage_Skidmore_2_%28cropped%29.jpg'),
('Chris Hemsworth', '1983-08-11', 'https://b.thumbs.redditmedia.com/5Hf5b-s0ruQdAWYgO5s9y9n11Hg3y_AT8eEjTHBIC2I.png'),
('Gal Gadot', '1985-04-30', 'https://vipcheck.wageindicator.org/media/416px-Gal_Gadot_cropped_lighting_corrected_2b.jpg'),
('Leonardo DiCaprio', '1974-11-11', 'https://cdn.britannica.com/65/227665-050-D74A477E/American-actor-Leonardo-DiCaprio-2016.jpg'),
('Natalie Portman', '1981-06-09', 'https://d26oc3sg82pgk3.cloudfront.net/files/media/edit/image/31089/square_thumb%402x.jpg'),
('Tom Hanks', '1956-07-09', 'https://www.venus.com.py/wp-content/uploads/2023/05/gettyimages-1257937597.jpg'),
('Morgan Freeman', '1937-06-01', 'https://www.lavanguardia.com/peliculas-series/images/profile/1937/6/w300/905k0RFzH0Kd6gx8oSxRdnr6FL.jpg'),
('Emma Watson', '1990-04-15', 'https://m.media-amazon.com/images/M/MV5BMTQ3ODE2NTMxMV5BMl5BanBnXkFtZTgwOTIzOTQzMjE@._V1_.jpg'),
('Will Smith', '1968-09-25', 'https://m.media-amazon.com/images/M/MV5BNTczMzk1MjU1MV5BMl5BanBnXkFtZTcwNDk2MzAyMg@@._V1_FMjpg_UX1000_.jpg'),
('Jennifer Lawrence', '1990-08-15', 'https://static.wikia.nocookie.net/doblaje/images/e/e2/Jennifer_Lawrence_2021.jpg/revision/latest/scale-to-width-down/1200?cb=20220614011818&path-prefix=es'),
('Christian Bale', '1974-01-30', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Christian_Bale-7837.jpg/1200px-Christian_Bale-7837.jpg'),
('Anne Hathaway', '1982-11-12', 'https://media.vogue.mx/photos/634ed7f6aff47fc4b2cf688a/2:3/w_2560%2Cc_limit/GettyImages-1434318387.jpg'),
('Brad Pitt', '1963-12-18', 'https://turkeyanaclinic.com/wp-content/uploads/2024/09/Brad-Pitt-2.jpg'),
('Angelina Jolie', '1975-06-04', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/Angelina_Jolie-643531_%28cropped%29.jpg/800px-Angelina_Jolie-643531_%28cropped%29.jpg'),
('Samuel L. Jackson', '1948-12-21', 'https://m.media-amazon.com/images/M/MV5BMTQ1NTQwMTYxNl5BMl5BanBnXkFtZTYwMjA1MzY1._V1_.jpg'),
('Zendaya', '1996-09-01', 'https://static.wikia.nocookie.net/doblaje/images/5/5c/Zendaya.jpg/revision/latest?cb=20220405231400&path-prefix=es'),
('Dwayne Johnson', '1972-05-02', 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Dwayne_Johnson_2%2C_2013.jpg/800px-Dwayne_Johnson_2%2C_2013.jpg'),
('Keanu Reeves', '1964-09-02', 'https://wradio.com.mx/resizer/v2/JNLV4MBMURFDJOJBGK26Z2QXF4.jpg?auth=5c2f50b7320193d344083e3564ba6d3c150b9892ea4422a18469093bbac3cfd7&width=650&quality=70&smart=true'),
('Sydney Sweeney', '1997-09-12', 'https://media.vogue.es/photos/660a8af86842a1a7859c8972/2:3/w_2560%2Cc_limit/GettyImages-2076716982.jpg');

