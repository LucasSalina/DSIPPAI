-- Tablas maestras
CREATE TABLE IF NOT EXISTS alcance_sismo (
  id_alcance INTEGER PRIMARY KEY AUTO_INCREMENT,
  descripcion TEXT,
  nombre TEXT
);

CREATE TABLE IF NOT EXISTS clasificacion_sismo (
  id_clasificacion INTEGER PRIMARY KEY AUTO_INCREMENT,
  km_profundidad_desde REAL,
  km_profundidad_hasta REAL,
  nombre TEXT
);

CREATE TABLE IF NOT EXISTS origen_de_generacion (
  id_origen INTEGER PRIMARY KEY AUTO_INCREMENT,
  descripcion TEXT,
  nombre TEXT
);

-- MODIFIED: Primary key column renamed to 'id' to match Estado.java entity
CREATE TABLE IF NOT EXISTS estado (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  ambito TEXT,
  nombre_estado TEXT
);

CREATE TABLE IF NOT EXISTS tipo_de_dato (
  id_tipo INTEGER PRIMARY KEY AUTO_INCREMENT,
  denominacion TEXT,
  nombre_unidad_medida TEXT,
  valor_umbral REAL
);

CREATE TABLE IF NOT EXISTS estacion_sismologica (
  codigo_estacion TEXT PRIMARY KEY,
  documento_cert_adq TEXT,
  fecha_solicitud_cert DATE,
  latitud REAL,
  longitud REAL,
  nombre TEXT,
  nro_cert_adquisicion TEXT
);

CREATE TABLE IF NOT EXISTS magnitud_ritcher (
  id_magnitud_ritcher INTEGER PRIMARY KEY AUTO_INCREMENT,
  valor REAL,
  descripcion TEXT
);

---

-- Tablas transaccionales
CREATE TABLE IF NOT EXISTS detalle_muestra_sismica (
  id_detalle_muestra_sismica INTEGER PRIMARY KEY AUTO_INCREMENT,
  id_tipo INTEGER REFERENCES TIPO_DE_DATO(id_tipo),
  valor REAL
);

CREATE TABLE IF NOT EXISTS sismografo (
  identificador TEXT PRIMARY KEY,
  id_adquisicion INTEGER,
  nro_serie TEXT,
  codigo_estacion TEXT REFERENCES ESTACION_SISMOLOGICA(codigo_estacion)
);

CREATE TABLE IF NOT EXISTS muestra_sismica (
  id_muestra INTEGER PRIMARY KEY AUTO_INCREMENT,
  fecha_hora_muestra DATETIME,
  id_detalle_muestra INTEGER REFERENCES DETALLE_MUESTRA_SISMICA(id_detalle_muestra_sismica)
);

-- EVENTO_SISMICO must be created before serie_temporal and cambio_estado
CREATE TABLE IF NOT EXISTS evento_sismico (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  fecha_hora_fin DATETIME,
  fecha_hora_ocurrencia DATETIME,
  latitud_epicentro REAL,
  longitud_epicentro REAL,
  latitud_hipocentro REAL,
  longitud_hipocentro REAL,
  valor_magnitud REAL,
  alcance_sismo_id INTEGER REFERENCES ALCANCE_SISMO(id_alcance),
  clasificacion_sismo_id INTEGER REFERENCES CLASIFICACION_SISMO(id_clasificacion),
  estado_actual_id INTEGER REFERENCES ESTADO(id), -- REFERENCES the new 'id' column in ESTADO
  magnitud_ritcher_id INTEGER REFERENCES MAGNITUD_RITCHER(id_magnitud_ritcher),
  origen_generacion_id INTEGER REFERENCES ORIGEN_DE_GENERACION(id_origen)
);

CREATE TABLE IF NOT EXISTS serie_temporal (
  id_serie INTEGER PRIMARY KEY AUTO_INCREMENT,
  condicion_alarma TEXT,
  fecha_hora_inicio_reg_muestreo DATETIME,
  fecha_hora_registros DATETIME,
  frecuencia_muestreo REAL,
  id_muestra_sismica INTEGER REFERENCES MUESTRA_SISMICA(id_muestra),
  id_sismografo TEXT REFERENCES SISMOGRAFO(identificador),
  evento_sismico_id INTEGER REFERENCES EVENTO_SISMICO(id)
);

CREATE TABLE IF NOT EXISTS cambio_estado (
  id_cambio INTEGER PRIMARY KEY AUTO_INCREMENT,
  id_estado INTEGER REFERENCES ESTADO(id), -- REFERENCES the new 'id' column in ESTADO
  fecha_hora_inicio DATETIME,
  fecha_hora_fin DATETIME,
  es_estado_actual INTEGER,
  sos_autodetectado INTEGER,
  sos_pendiente_revision INTEGER,
  evento_sismico_id INTEGER REFERENCES EVENTO_SISMICO(id)
);