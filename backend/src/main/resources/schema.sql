-- Tablas maestras
CREATE TABLE IF NOT EXISTS alcance_sismo (
  id_alcance          INTEGER PRIMARY KEY AUTO_INCREMENT,
  descripcion         TEXT,
  nombre              TEXT
);

CREATE TABLE IF NOT EXISTS clasificacion_sismo (
  id_clasificacion    INTEGER PRIMARY KEY AUTO_INCREMENT,
  km_profundidad_desde REAL,
  km_profundidad_hasta REAL,
  nombre              TEXT
);

CREATE TABLE IF NOT EXISTS origen_de_generacion (
  id_origen           INTEGER PRIMARY KEY AUTO_INCREMENT,
  descripcion         TEXT,
  nombre              TEXT
);

CREATE TABLE IF NOT EXISTS estado (
  id_estado           INTEGER PRIMARY KEY AUTO_INCREMENT,
  ambito              TEXT,
  nombre_estado       TEXT
);

CREATE TABLE IF NOT EXISTS tipo_de_dato (
  id_tipo             INTEGER PRIMARY KEY AUTO_INCREMENT,
  denominacion        TEXT,
  nombre_unidad_medida TEXT,
  valor_umbral        REAL
);

CREATE TABLE IF NOT EXISTS estacion_sismologica (
  codigo_estacion     TEXT PRIMARY KEY,
  documento_cert_adq  TEXT,
  fecha_solicitud_cert DATE,
  latitud             REAL,
  longitud            REAL,
  nombre              TEXT,
  nro_cert_adquisicion TEXT
);

-- Tablas transaccionales
CREATE TABLE IF NOT EXISTS detalle_muestra_sismica (
  id_detalle_muestra_sismica   INTEGER PRIMARY KEY AUTO_INCREMENT,
  id_tipo                      INTEGER REFERENCES TIPO_DE_DATO(id_tipo),
  valor                        REAL
);

CREATE TABLE IF NOT EXISTS sismografo (
  identificador       TEXT PRIMARY KEY,
  id_adquisicion      INTEGER,
  nro_serie           TEXT,
  codigo_estacion     TEXT REFERENCES ESTACION_SISMOLOGICA(codigo_estacion)
);

CREATE TABLE IF NOT EXISTS muestra_sismica (
  id_muestra          INTEGER PRIMARY KEY AUTO_INCREMENT,
  fecha_hora_muestra  DATETIME,
  id_detalle_muestra  INTEGER REFERENCES DETALLE_MUESTRA_SISMICA(id_detalle_muestra_sismica)
);

CREATE TABLE IF NOT EXISTS serie_temporal (
  id_serie                     INTEGER PRIMARY KEY AUTO_INCREMENT,
  condicion_alarma             TEXT,
  fecha_hora_inicio_reg_muestreo DATETIME,
  fecha_hora_registros         DATETIME,
  frecuencia_muestreo          REAL,
  id_muestra_sismica           INTEGER REFERENCES MUESTRA_SISMICA(id_muestra),
  id_sismografo                TEXT    REFERENCES SISMOGRAFO(identificador)
);

CREATE TABLE IF NOT EXISTS evento_sismico (
  id_evento_sismico          INTEGER PRIMARY KEY AUTO_INCREMENT,
  fecha_hora_fin             DATETIME,
  fecha_hora_ocurrencia      DATETIME,
  latitud_epicentro          REAL,
  longitud_epicentro         REAL,
  latitud_hipocentro         REAL, -- ¡Añadida esta columna!
  longitud_hipocentro        REAL,
  valor_magnitud             REAL,
  id_alcance                 INTEGER REFERENCES ALCANCE_SISMO(id_alcance),
  id_clasificacion           INTEGER REFERENCES CLASIFICACION_SISMO(id_clasificacion),
  id_estado_actual           INTEGER REFERENCES ESTADO(id_estado),
  id_serie                   INTEGER REFERENCES SERIE_TEMPORAL(id_serie)
);

CREATE TABLE IF NOT EXISTS cambio_estado (
  id_cambio                  INTEGER PRIMARY KEY AUTO_INCREMENT,
  id_evento                  INTEGER REFERENCES EVENTO_SISMICO(id_evento_sismico),
  id_estado                  INTEGER REFERENCES ESTADO(id_estado),
  fecha_hora_inicio          DATETIME,
  fecha_hora_fin             DATETIME,
  es_estado_actual           INTEGER,
  sos_autodetectado          INTEGER,
  sos_pendiente_revision     INTEGER
);
