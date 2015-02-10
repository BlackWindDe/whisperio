/*-------------------------------------------------------*/
/*  Script to create the database used by Whisper.io   . */
/*-------------------------------------------------------*/

CREATE USER 'whisperio'@'localhost' IDENTIFIED BY 'whisperio';
CREATE DATABASE IF NOT EXISTS whisperio;
GRANT ALL PRIVILEGES ON whisperio . * TO 'whisperio'@'localhost';
