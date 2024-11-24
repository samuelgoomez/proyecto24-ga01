CREATE DATABASE "Visualization";

\c Visualization

CREATE TABLE visualizations (
    visualizationID SERIAL PRIMARY KEY,     
    userID INTEGER NOT NULL,                
    filmID INTEGER DEFAULT NULL,                        
    serieID INTEGER DEFAULT NULL,
    visualizationDate TIMESTAMP DEFAULT NOW(),
    progreso VARCHAR(50) DEFAULT 'Viendo',        
    estado VARCHAR(50) NOT NULL DEFAULT 'Reproduciendo',             
    idioma VARCHAR(50) DEFAULT 'español',    
    subtitulos BOOLEAN DEFAULT FALSE,       

	CHECK (
        (filmID IS NOT NULL AND serieID IS NULL) OR
        (filmID IS NULL AND serieID IS NOT NULL)
    )
);

INSERT INTO visualizations (userID,filmID,serieID,progreso,estado,idioma,subtitulos) VALUES 
(1,1,NULL,'Viendo', 'Reproduciendo', 'Español', FALSE),
(2,NULL,1,'Viendo', 'Reproduciendo', 'Español', FALSE);