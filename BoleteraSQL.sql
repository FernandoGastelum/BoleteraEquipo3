-- Creación de base de datos
CREATE DATABASE IF NOT EXISTS SistemaReventaBoletos;
USE SistemaReventaBoletos;

-- Tabla de usuarios
CREATE TABLE Usuarios (
    usuario_id INT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(20) NOT NULL,
    nombre_completo VARCHAR(255) NOT NULL,
    domicilio VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    edad INT NOT NULL,
    saldo DECIMAL(10, 2) DEFAULT 0.00
);
-- Tabla de eventos
CREATE TABLE Eventos (
    evento_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_evento VARCHAR(255) NOT NULL,
    fecha_evento DATE NOT NULL,
    venue VARCHAR(255) NOT NULL,
    ciudad VARCHAR(255) NOT NULL,
    estado VARCHAR(100) NOT NULL,
    descripcion TEXT
);

-- Tabla de boletos
CREATE TABLE Boletos (
    boleto_id INT AUTO_INCREMENT PRIMARY KEY,
    numero_serie VARCHAR(8) NOT NULL UNIQUE,
    fila VARCHAR(10) NOT NULL,
    asiento VARCHAR(10) NOT NULL,
    numero_control INT NOT NULL,
    precio_original DECIMAL(10, 2) NOT NULL,
    precio_actual DECIMAL(10, 2) NOT NULL,
    evento_id INT NOT NULL,
    usuario_id INT,
    reventa BOOLEAN NOT NULL DEFAULT 0,
    venta BOOLEAN NOT NULL DEFAULT 0,
    FOREIGN KEY (evento_id) REFERENCES Eventos(evento_id),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id)
);

-- Tabla de transacciones
CREATE TABLE Transacciones (
    transaccion_id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_transaccion ENUM('compra', 'reventa') NOT NULL,
    fecha_hora DATETIME NOT NULL,
    comprador_id INT,
    vendedor_id INT,
    monto DECIMAL(10, 2) NOT NULL,
    boleto_id INT NOT NULL,
    comision DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (comprador_id) REFERENCES Usuarios(usuario_id),
    FOREIGN KEY (vendedor_id) REFERENCES Usuarios(usuario_id),
    FOREIGN KEY (boleto_id) REFERENCES Boletos(boleto_id)
);

-- Tabla para reservas temporales
CREATE TABLE Reservas (
    reserva_id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    boleto_id INT NOT NULL,
    fecha_reserva DATETIME NOT NULL,
    tiempo_expiracion DATETIME NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(usuario_id),
    FOREIGN KEY (boleto_id) REFERENCES Boletos(boleto_id)
);

DELIMITER //

CREATE FUNCTION GenerarNumeroSerie() 
RETURNS CHAR(8) 
NOT DETERMINISTIC 
NO SQL
BEGIN
    DECLARE caracteres CHAR(62) DEFAULT '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
    DECLARE resultado CHAR(8) DEFAULT '';
    DECLARE i INT DEFAULT 0;

    WHILE i < 8 DO
        SET resultado = CONCAT(resultado, SUBSTRING(caracteres, FLOOR(1 + RAND() * 62), 1));
        SET i = i + 1;
    END WHILE;

    RETURN resultado;
END //

DELIMITER ;

-- Disparador para generar número de serie único al insertar un nuevo boleto
DELIMITER //
CREATE TRIGGER GenerarSerieBoleto BEFORE INSERT ON Boletos 
FOR EACH ROW

BEGIN
    SET NEW.numero_serie = GenerarNumeroSerie();
END //
DELIMITER ;

-- Procedimiento para realizar la compra de boletos
DELIMITER //
CREATE PROCEDURE ComprarBoletos(
    IN p_comprador_id INT,
    IN p_boleto_id INT,
    IN p_monto DECIMAL(10, 2)
)
BEGIN
    DECLARE v_precio DECIMAL(10, 2);
    DECLARE v_vendedor_id INT;
    DECLARE v_comision DECIMAL(10, 2);

    -- Obtener el precio y el vendedor del boleto
    SELECT precio_original, usuario_id INTO v_precio, v_vendedor_id
    FROM Boletos WHERE boleto_id = p_boleto_id;

    -- Calcular la comisión del 3%
    SET v_comision = p_monto * 0.03;

    -- Verificar si el comprador tiene suficiente saldo
    IF (SELECT saldo FROM Usuarios WHERE usuario_id = p_comprador_id) >= p_monto THEN
        -- Actualizar saldos
        UPDATE Usuarios SET saldo = saldo - p_monto WHERE usuario_id = p_comprador_id;
        UPDATE Usuarios SET saldo = saldo + (p_monto - v_comision) WHERE usuario_id = v_vendedor_id;

        -- Registrar transacción
        INSERT INTO Transacciones(tipo_transaccion, fecha_hora, comprador_id, vendedor_id, monto, boleto_id, comision)
        VALUES ('compra', NOW(), p_comprador_id, v_vendedor_id, p_monto, p_boleto_id, v_comision);

        -- Transferir el boleto al comprador
        UPDATE Boletos SET usuario_id = p_comprador_id, fecha_adquisicion = NOW(), reventa = 1 WHERE boleto_id = p_boleto_id;
    -- ELSE
        -- Si no tiene suficiente saldo, reservar el boleto por 10 minutos
         -- INSERT INTO Reservas(usuario_id, boleto_id, fecha_reserva, tiempo_expiracion)
         -- VALUES (p_comprador_id, p_boleto_id, NOW(), DATE_ADD(NOW(), INTERVAL 10 MINUTE));
    END IF;
END //
DELIMITER ;

DELIMITER //

CREATE PROCEDURE PonerBoletoEnReventa(
    IN p_usuario_id INT,
    IN p_boleto_id INT,
    IN p_precio_reventa DECIMAL(10, 2),
    IN p_fecha_limite DATE
)
BEGIN
    DECLARE v_precio_original DECIMAL(10, 2);
    
    -- Iniciar la transacción
    START TRANSACTION;

    
    BEGIN
    -- Manejar errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
        -- Si ocurre algún error, deshacer la transacción
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al procesar la transacción.';
    END;

    -- Obtener el precio original del boleto
    SELECT precio_original INTO v_precio_original
    FROM Boletos WHERE boleto_id = p_boleto_id;

    -- Verificar que el precio de reventa no sea mayor al 3% del original
    IF p_precio_reventa <= (v_precio_original * 1.03) THEN
        -- Actualizar el precio del boleto para reventa
        UPDATE Boletos 
        SET precio_original = p_precio_reventa, reventa = 1 
        WHERE boleto_id = p_boleto_id AND usuario_id = p_usuario_id;
        
        -- Confirmar la transacción
        COMMIT;
    ELSE
        -- Enviar un mensaje de error si el precio de reventa excede el límite
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El precio de reventa no puede exceder el 3% del precio original.';
    END IF;
    
END //
DELIMITER ;
ALTER TABLE Boletos ADD precio_actual DECIMAL(10, 2) NOT NULL;


INSERT INTO Boletos (numero_serie, fila, asiento, numero_control, precio_original,precio_actual, evento_id, reventa, venta) VALUES ("", "2", "4", 2513, 1000, 1000, 1, 0, 1);
INSERT INTO Boletos (numero_serie, fila, asiento, numero_control, precio_original,precio_actual, evento_id, reventa, venta) VALUES ("", "1", "1", 1884, 1000, 1000, 2, 0, 1);
INSERT INTO Boletos (numero_serie, fila, asiento, numero_control, precio_original,precio_actual, evento_id, reventa, venta) VALUES ("", "1", "4", 1684, 1000, 1000, 3, 0, 1);
insert into Usuarios (correo, contraseña, nombre_completo, domicilio, fecha_nacimiento, edad) VALUES ("ejemplo@gmail.com","password123","James Earl Cash","direccion ejemplo",STR_TO_DATE('12-05-2002', '%d-%m-%Y'),22);
insert into Usuarios (correo, contraseña, nombre_completo, domicilio, fecha_nacimiento, edad) VALUES ("ejemplo2@gmail.com","password123","Marlon Balmaceda","direccion ejemplo",STR_TO_DATE('05-04-2001', '%d-%m-%Y'),23);

insert into eventos (nombre_evento, fecha_evento, venue, ciudad, estado, descripcion) VALUES ("Julie",STR_TO_DATE("17-10-2024", '%d-%m-%Y'),"The Observatory North Park","San Diego","California","This event is open to all ages. All guests under the age of 18 must be accompanied by a parent, guardian, or responsible adult over the age of 25 with written authorization.
");
insert into eventos (nombre_evento, fecha_evento, venue, ciudad, estado, descripcion) VALUES ("LINKIN PARK",STR_TO_DATE("11-11-2024", '%d-%m-%Y'),"Coliseo MedPlus","Bogota","Colombia"," Los menores de 14 años deben asistir al evento en compañía de un adulto responsable, que haya comprado tickets de la misma localidad.");

insert into eventos (nombre_evento, fecha_evento, venue, ciudad, estado, descripcion) VALUES ("Los Temerarios",STR_TO_DATE("10-12-2024", '%d-%m-%Y'),"Estadio GNP Seguros","Mexico City","Mexico","Los Temerarios cierran una era de música popular mexicana en el Estadio GNP Seguros");
	
