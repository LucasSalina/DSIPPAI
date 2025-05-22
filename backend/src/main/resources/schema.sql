-- Tablas maestras
CREATE TABLE IF NOT EXISTS ALCANCE_SISMO (
  id_alcance         INTEGER AUTO_INCREMENT PRIMARY KEY,
  descripcion        VARCHAR(255),
  nombre             VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS CLASIFICACION_SISMO (
  id_clasificacion      INTEGER AUTO_INCREMENT PRIMARY KEY,
  km_profundidad_desde  DOUBLE,
  km_profundidad_hasta  DOUBLE,
  nombre                VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS ORIGEN_DE_GENERACION (
  id_origen          INTEGER AUTO_INCREMENT PRIMARY KEY,
  descripcion        VARCHAR(255),
  nombre             VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS ESTADO (
  id_estado          INTEGER AUTO_INCREMENT PRIMARY KEY,
  ambito             VARCHAR(255),
  nombre_estado      VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS TIPO_DE_DATO (
  id_tipo               INTEGER AUTO_INCREMENT PRIMARY KEY,
  denominacion          VARCHAR(255),
  nombre_unidad_medida  VARCHAR(255),
  valor_umbral          DOUBLE
);

CREATE TABLE IF NOT EXISTS ESTACION_SISMOLOGICA (
  codigo_estacion       VARCHAR(255) PRIMARY KEY,
  documento_cert_adq    VARCHAR(255),
  fecha_solicitud_cert  VARCHAR(255),
  latitud               DOUBLE,
  longitud              DOUBLE,
  nombre                VARCHAR(255),
  nro_cert_adquisicion  VARCHAR(255)
);

-- Tablas transaccionales
CREATE TABLE IF NOT EXISTS EVENTO_SISMICO (
  id_evento_sismico       INTEGER PRIMARY KEY AUTOINCREMENT,
  fecha_hora_fin          TEXT,
  fecha_hora_ocurrencia   TEXT,
  latitud_epicentro       REAL,
  longitud_epicentro      REAL,
  latitud_hipocentro      REAL,
  longitud_hipocentro     REAL,
  valor_magnitud          REAL,
  id_alcance              INTEGER REFERENCES ALCANCE_SISMO(id_alcance),
  id_clasificacion        INTEGER REFERENCES CLASIFICACION_SISMO(id_clasificacion),
  id_estado_actual        INTEGER REFERENCES ESTADO(id_estado),
  id_serie                INTEGER REFERENCES SERIE_TEMPORAL(id_serie)
);

CREATE TABLE IF NOT EXISTS CAMBIO_ESTADO (
  id_cambio               INTEGER PRIMARY KEY AUTOINCREMENT,
  id_evento               INTEGER REFERENCES EVENTO_SISMICO(id_evento_sismico),
  id_estado               INTEGER REFERENCES ESTADO(id_estado),
  fecha_hora_inicio       TEXT,
  fecha_hora_fin          TEXT,
  es_estado_actual        INTEGER,
  sos_autodetectado       INTEGER,
  sos_pendiente_revision  INTEGER
);

CREATE TABLE IF NOT EXISTS SISMOGRAFO (
  identificador        TEXT PRIMARY KEY,
  id_adquisicion       INTEGER,
  nro_serie            TEXT,
  codigo_estacion      TEXT REFERENCES ESTACION_SISMOLOGICA(codigo_estacion)
);

CREATE TABLE IF NOT EXISTS MUESTRA_SISMICA (
  id_muestra                INTEGER PRIMARY KEY AUTOINCREMENT,
  fecha_hora_muestra        TEXT,
  id_detalle_muestra        INTEGER REFERENCES DETALLE_MUESTRA_SISMICA(id_detalle_muestra_sismica)
);

CREATE TABLE IF NOT EXISTS SERIE_TEMPORAL (
  id_serie                       INTEGER PRIMARY KEY AUTOINCREMENT,
  condicion_alarma               TEXT,
  fecha_hora_inicio_reg_muestreo TEXT,
  fecha_hora_registros           TEXT,
  frecuencia_muestreo            REAL,
  id_muestra_sismica             INTEGER REFERENCES MUESTRA_SISMICA(id_muestra),
  id_sismografo                  TEXT    REFERENCES SISMOGRAFO(identificador)
);

CREATE TABLE IF NOT EXISTS DETALLE_MUESTRA_SISMICA (
  id_detalle_muestra_sismica     INTEGER PRIMARY KEY AUTOINCREMENT,
  id_tipo                        INTEGER REFERENCES TIPO_DE_DATO(id_tipo),
  valor                          REAL
);
