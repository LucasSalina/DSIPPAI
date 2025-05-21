PRAGMA foreign_keys = ON;

-- Tablas maestras
CREATE TABLE ALCANCE_SISMO (
  id_alcance         INTEGER PRIMARY KEY AUTOINCREMENT,
  descripcion        TEXT,
  nombre             TEXT
);

CREATE TABLE CLASIFICACION_SISMO (
  id_clasificacion      INTEGER PRIMARY KEY AUTOINCREMENT,
  km_profundidad_desde  REAL,
  km_profundidad_hasta  REAL,
  nombre                TEXT
);

CREATE TABLE ORIGEN_DE_GENERACION (
  id_origen          INTEGER PRIMARY KEY AUTOINCREMENT,
  descripcion        TEXT,
  nombre             TEXT
);

CREATE TABLE ESTADO (
  id_estado          INTEGER PRIMARY KEY AUTOINCREMENT,
  ambito             TEXT,
  nombre_estado      TEXT
);

CREATE TABLE TIPO_DE_DATO (
  id_tipo               INTEGER PRIMARY KEY AUTOINCREMENT,
  denominacion          TEXT,
  nombre_unidad_medida  TEXT,
  valor_umbral          REAL
);

CREATE TABLE ESTACION_SISMOLOGICA (
  codigo_estacion       TEXT PRIMARY KEY,
  documento_cert_adq    TEXT,
  fecha_solicitud_cert  TEXT,
  latitud               REAL,
  longitud              REAL,
  nombre                TEXT,
  nro_cert_adquisicion  TEXT
);

-- Tablas transaccionales
CREATE TABLE EVENTO_SISMICO (
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

CREATE TABLE CAMBIO_ESTADO (
  id_cambio               INTEGER PRIMARY KEY AUTOINCREMENT,
  id_evento               INTEGER REFERENCES EVENTO_SISMICO(id_evento_sismico),
  id_estado               INTEGER REFERENCES ESTADO(id_estado),
  fecha_hora_inicio       TEXT,
  fecha_hora_fin          TEXT,
  es_estado_actual        INTEGER,
  sos_autodetectado       INTEGER,
  sos_pendiente_revision  INTEGER
);

CREATE TABLE SISMOGRAFO (
  identificador        TEXT PRIMARY KEY,
  id_adquisicion       INTEGER,
  nro_serie            TEXT,
  codigo_estacion      TEXT REFERENCES ESTACION_SISMOLOGICA(codigo_estacion)
);

CREATE TABLE MUESTRA_SISMICA (
  id_muestra                INTEGER PRIMARY KEY AUTOINCREMENT,
  fecha_hora_muestra        TEXT,
  id_detalle_muestra        INTEGER REFERENCES DETALLE_MUESTRA_SISMICA(id_detalle_muestra_sismica)
);

CREATE TABLE SERIE_TEMPORAL (
  id_serie                       INTEGER PRIMARY KEY AUTOINCREMENT,
  condicion_alarma               TEXT,
  fecha_hora_inicio_reg_muestreo TEXT,
  fecha_hora_registros           TEXT,
  frecuencia_muestreo            REAL,
  id_muestra_sismica             INTEGER REFERENCES MUESTRA_SISMICA(id_muestra),
  id_sismografo                  TEXT    REFERENCES SISMOGRAFO(identificador)
);

CREATE TABLE DETALLE_MUESTRA_SISMICA (
  id_detalle_muestra_sismica     INTEGER PRIMARY KEY AUTOINCREMENT,
  id_tipo                        INTEGER REFERENCES TIPO_DE_DATO(id_tipo),
  valor                          REAL
);
