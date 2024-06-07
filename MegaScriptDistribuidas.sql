CREATE DATABASE Proyecto;

USE Proyecto;

-- Tabla de Clientes
CREATE TABLE Cliente (
    ruc VARCHAR(13) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(150)
);

-- Tabla de Ciudad de Entrega
CREATE TABLE CiudadEntrega (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla de Artículos
CREATE TABLE Articulo (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL
);

-- Tabla de Tipo de Movimiento de Inventario
CREATE TABLE TipoMovimiento (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    signo CHAR(1) NOT NULL CHECK (signo IN ('I', 'E'))
);

-- Tabla de Usuarios
CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

-- Tabla de Cabecera de Factura
CREATE TABLE CabeceraFactura (
    numero INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    ciudad_entrega_id INT,
    FOREIGN KEY (ciudad_entrega_id) REFERENCES CiudadEntrega(codigo)
);

-- Tabla de Detalle de Factura
CREATE TABLE DetalleFactura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_factura INT,
    articulo_id INT,
    cantidad INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (numero_factura) REFERENCES CabeceraFactura(numero),
    FOREIGN KEY (articulo_id) REFERENCES Articulo(codigo)
);

-- Tabla de Cabecera de Comprobante de Inventario
CREATE TABLE CabeceraComprobanteInventario (
    numero INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    tipo_movimiento_id INT,
    FOREIGN KEY (tipo_movimiento_id) REFERENCES TipoMovimiento(codigo)
);

-- Tabla de Detalle de Comprobante de Inventario
CREATE TABLE DetalleComprobanteInventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_comprobante INT,
    articulo_id INT,
    cantidad INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (numero_comprobante) REFERENCES CabeceraComprobanteInventario(numero),
    FOREIGN KEY (articulo_id) REFERENCES Articulo(codigo)
);

-- Tabla de Cobradores
CREATE TABLE Cobrador (
    cedula VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(150)
);

-- Tabla de Formas de Pago
CREATE TABLE FormaPago (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla de Cabecera de Factura de Cuentas por Cobrar
CREATE TABLE CabeceraCxc (
    numero_factura INT PRIMARY KEY,
    fecha DATE NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    cliente_ruc VARCHAR(13),
    FOREIGN KEY (cliente_ruc) REFERENCES Cliente(ruc)
);

-- Tabla de Detalle de Pagos de Cuentas por Cobrar
CREATE TABLE DetallePago (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_factura INT,
    fecha_pago DATE NOT NULL,
    forma_pago_id INT,
    valor DECIMAL(10, 2) NOT NULL,
    cobrador_id VARCHAR(10),
    FOREIGN KEY (numero_factura) REFERENCES CabeceraCxc(numero_factura),
    FOREIGN KEY (forma_pago_id) REFERENCES FormaPago(codigo),
    FOREIGN KEY (cobrador_id) REFERENCES Cobrador(cedula)
);

-- Tabla de Tipos de Cuenta para Contabilidad
CREATE TABLE TipoCuenta (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla de Cuentas para Contabilidad
CREATE TABLE Cuenta (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo_cuenta_id INT,
    FOREIGN KEY (tipo_cuenta_id) REFERENCES TipoCuenta(codigo)
);

-- Tabla de Cabecera de Comprobante de Contabilidad
CREATE TABLE CabeceraComprobanteContabilidad (
    numero INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    observaciones VARCHAR(255)
);

-- Tabla de Detalle de Comprobante de Contabilidad
CREATE TABLE DetalleComprobanteContabilidad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_comprobante INT,
    cuenta_id INT,
    cantidad_debe DECIMAL(10, 2) NOT NULL,
    cantidad_haber DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (numero_comprobante) REFERENCES CabeceraComprobanteContabilidad(numero),
    FOREIGN KEY (cuenta_id) REFERENCES Cuenta(codigo)
);

-- Asegúrate de crear índices y restricciones adicionales según sea necesario para tu proyecto

