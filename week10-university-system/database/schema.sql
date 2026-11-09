-- Run this in MySQL Workbench (File > Open SQL Script, then the lightning button)
CREATE DATABASE IF NOT EXISTS university_db;
USE university_db;

-- Table 1: Programs (degrees offered)
CREATE TABLE IF NOT EXISTS programs (
    program_id INT AUTO_INCREMENT PRIMARY KEY,   -- Unique ID for the program
    program_name VARCHAR(100) NOT NULL,          -- Name of the degree
    required_units INT NOT NULL                  -- How many units to graduate
);

-- Sample programs (only inserted if the table is empty)
INSERT INTO programs (program_name, required_units)
SELECT 'Bachelor of Computer Science', 56
WHERE NOT EXISTS (SELECT 1 FROM programs);
INSERT INTO programs (program_name, required_units)
SELECT 'Diploma in IT', 24
WHERE (SELECT COUNT(*) FROM programs) < 2;

-- Table 2: Students
CREATE TABLE IF NOT EXISTS students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,         -- Unique ID for the student
    registration_number VARCHAR(20) UNIQUE NOT NULL,   -- School ID (e.g., S101)
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    program_id INT,                                    -- Links student to a program
    FOREIGN KEY (program_id) REFERENCES programs(program_id)
);
