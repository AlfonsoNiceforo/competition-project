CREATE TABLE statistics (
    playerName1 VARCHAR(255) NOT NULL,
    playerName2 VARCHAR(255) NOT NULL,
    timesTogether INT NOT NULL DEFAULT 1,
    PRIMARY KEY (playerName1, playerName2)
);