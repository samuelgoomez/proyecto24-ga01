-- Crear la base de datos "user"
CREATE DATABASE "User";

-- Conectar a la base de datos "user"
\c "User";

CREATE TABLE users (
    userID SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    "password" VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    "name" VARCHAR(100),
    createdAt TIMESTAMP DEFAULT NOW(),
    preferences INT[] DEFAULT NULL
);



INSERT INTO users (username, "password", email, "name",preferences)
VALUES ('toñop123', 'ttpp11', 'ttpp@gmail.com', 'Toño Perez',ARRAY[1, 2, 3]);

INSERT INTO users (username, "password", email, "name",preferences)
VALUES ('cris7', 'ccrr22', 'ccrr@gmail.com', 'Cristiano Ronaldo',ARRAY[1]);


CREATE TABLE profiles (
    profileID SERIAL PRIMARY KEY,
    userID INTEGER NOT NULL,
    "name" VARCHAR(100) NOT NULL,
    "type" VARCHAR(10) NOT NULL,
    avatarURL TEXT,
    createdAt TIMESTAMP DEFAULT NOW(),
	FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE
);

INSERT INTO profiles ( userID, name, type, avatarURL, createdAt) VALUES
-- Primer perfil
(1, 'Principal', 'adult', 'https://www.excelsior.com.mx/media/pictures/2024/11/21/3216015.jpghttps://www.excelsior.com.mx/media/pictures/2024/11/21/3216015.jpg', '2023-10-09T10:20:30Z'),
-- Segundo perfil
(2, 'Cristiano', 'adult', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMSGTtrTDtuCpVMqlcS8XLV61ORiQmOSCJUQ&s', '2023-11-24T12:00:00Z');



CREATE TABLE payments (
    paymentID SERIAL PRIMARY KEY,
    userID INTEGER NOT NULL,
    cardNumber INTEGER NOT NULL,
    expirationDate DATE NOT NULL,
    cardHolderName VARCHAR(100) NOT NULL,
	FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE
);

INSERT INTO payments (userID, cardNumber, expirationDate, cardHolderName) VALUES
-- Método de pago para el usuario 1
(1, 1234, '2025-12-31', 'Tono Perez'),
-- Método de pago para el usuario 2
(2, 5678, '2026-11-30', 'Cristiano Ronaldo');


CREATE TABLE subPlans (
    planID INTEGER NOT NULL,
    userID INTEGER NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    paymentID INTEGER NOT NULL,
	FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE,
	FOREIGN KEY (paymentID) REFERENCES payments(paymentID) ON DELETE SET NULL
);

INSERT INTO subPlans (planID, userID, startDate, endDate, paymentID) VALUES
-- Plan para el usuario 1
(1, 1, '2024-9-09', '2024-10-09', 1),
-- Plan para el usuario 2
(2, 2, '2024-10-01', '2024-11-01', 2);

CREATE TABLE lists (
    listID SERIAL PRIMARY KEY,
    userID INTEGER NOT NULL,
    "name" VARCHAR(100) NOT NULL,
    films INT[] DEFAULT ARRAY[]::INT[],
    series INT[] DEFAULT ARRAY[]::INT[],
    createdAt TIMESTAMP DEFAULT NOW(),
	FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE
);

-- Insertar listas para los usuarios
INSERT INTO lists (userID, name, films, series, createdAt) VALUES
-- Lista para el usuario 1: Mis Series Favoritas
(1, 'Mis Series Favoritas', ARRAY[0], ARRAY[1, 2, 3], '2024-11-24T10:00:00Z'),
-- Lista para el usuario 2: Mis Películas Favoritas
(2, 'Mis Películas Favoritas', ARRAY[1, 2, 3], ARRAY[0], '2024-11-24T10:00:00Z');



