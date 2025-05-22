
-- Datos maestras
INSERT INTO alcance_sismo (id_alcance, descripcion, nombre) VALUES
  (1, 'Local, bajo 50km','Local'),
  (2, 'Regional, 50–200km','Regional'),
  (3, 'Global, >200km','Global');

INSERT INTO clasificacion_sismo (id_clasificacion, km_profundidad_desde, km_profundidad_hasta, nombre) VALUES
  (1, 0,70,'Superficial'),
  (2, 70,300,'Intermedio'),
  (3, 300,700,'Profundo');

INSERT INTO origen_de_generacion (id_origen, descripcion, nombre) VALUES
  (1, 'Tectónico','Tectónico'),
  (2, 'Volcánico','Volcánico'),
  (3, 'Artificial','Artificial');

INSERT INTO estado (id, ambito, nombre_estado) VALUES
  (1, 'EventoSismico','AutoDetectado'),
  (2, 'EventoSismico','PendienteRevision'),
  (3, 'EventoSismico','BloqueadoEnRevision'),
  (4, 'EventoSismico','Rechazado'),
  (5, 'EventoSismico','Aprobado');

INSERT INTO tipo_de_dato (id_tipo, denominacion, nombre_unidad_medida, valor_umbral) VALUES
  (1, 'Aceleración','g',0.01),
  (2, 'Velocidad','m/s',0.001),
  (3, 'Desplazamiento','mm',0.1);

INSERT INTO estacion_sismologica (codigo_estacion, documento_cert_adq, fecha_solicitud_cert, latitud, longitud, nombre, nro_cert_adquisicion) VALUES
  ('EST-001','DOC-1001','2023-01-15',40.41,-3.70,'Madrid Centro','CERT-2001'),
  ('EST-002','DOC-1002','2023-02-20',41.38,2.17,'Barcelona','CERT-2002'),
  ('EST-003','DOC-1003','2023-03-05',37.98,-1.13,'Alicante','CERT-2003');

INSERT INTO magnitud_ritcher (id_magnitud_ritcher, valor, descripcion) VALUES
  (1, 4.5, 'Ritcher 4.5'),
  (2, 5.0, 'Ritcher 5.0'),
  (3, 3.8, 'Ritcher 3.8');

-- NEW: Empleado data - REQUIRED for GestorRevisionManual initialization
INSERT INTO empleado (id, apellido, mail, nombre, telefono) VALUES
  (1, 'Perez', 'juan.perez@example.com', 'Juan', 1122334455),
  (2, 'Gomez', 'maria.gomez@example.com', 'Maria', 9876543210);

-- Sismógrafos
INSERT INTO sismografo (identificador, id_adquisicion, nro_serie, codigo_estacion) VALUES
  ('SISMO-A',101,'SN-A001','EST-001'),
  ('SISMO-B',102,'SN-B001','EST-002'),
  ('SISMO-C',103,'SN-C001','EST-003');

-- Detalles de muestra
INSERT INTO detalle_muestra_sismica (id_detalle_muestra_sismica, id_tipo, valor) VALUES
  (1,1,0.015),
  (2,1,0.020),
  (3,2,0.003),
  (4,3,0.120),
  (5,2,0.005);

-- Muestras
INSERT INTO muestra_sismica (id_muestra, fecha_hora_muestra, id_detalle_muestra) VALUES
  (1,'2025-05-01 08:00:00',1),
  (2,'2025-05-01 08:00:01',2),
  (3,'2025-05-01 08:00:02',3),
  (4,'2025-05-01 08:00:03',4),
  (5,'2025-05-01 08:00:04',5);

-- Eventos sísmicos (must be before serie_temporal and cambio_estado if they reference it)
INSERT INTO evento_sismico (
    id, -- Explicitly setting IDs for consistency with foreign keys
    fecha_hora_fin,
    fecha_hora_ocurrencia,
    latitud_epicentro,
    longitud_epicentro,
    latitud_hipocentro,
    longitud_hipocentro,
    valor_magnitud,
    alcance_sismo_id,
    clasificacion_sismo_id,
    estado_actual_id,
    magnitud_ritcher_id,
    origen_generacion_id
) VALUES
  (1, '2025-05-01 08:01:00','2025-05-01 08:00:00',40.42,-3.69,39.90,-3.50,4.5,1,1,1,1,1),
  (2, '2025-05-01 09:01:00','2025-05-01 09:00:00',41.39,2.16,40.00,1.50,5.0,2,2,2,2,2),
  (3, '2025-05-01 10:01:00','2025-05-01 10:00:00',37.99,-1.12,36.50,-1.00,3.8,3,3,1,3,3);

-- Series temporales
INSERT INTO serie_temporal (id_serie, condicion_alarma, fecha_hora_inicio_reg_muestreo, fecha_hora_registros, frecuencia_muestreo, id_muestra_sismica, id_sismografo, evento_sismico_id) VALUES
  (1, 'OK','2025-05-01 08:00:00','2025-05-01 08:00:10',100.0,1,'SISMO-A',1),
  (2, 'ALARMA','2025-05-01 09:00:00','2025-05-01 09:00:10',50.0,2,'SISMO-B',2),
  (3, 'OK','2025-05-01 10:00:00','2025-05-01 10:00:10',200.0,3,'SISMO-C',3);

-- Cambios de estado
INSERT INTO cambio_estado (id, evento_sismico_id, estado_id, fecha_hora_inicio, fecha_hora_fin, responsable_id) VALUES
  (1, 1, 1, '2025-05-01 08:00:00', '2025-05-01 08:00:30', NULL), -- Evento 1: AutoDetectado -> PendienteRevision (no responsable for initial auto-detect)
  (2, 1, 2, '2025-05-01 08:00:30', '2025-05-01 08:01:00', 1),  -- Evento 1: PendienteRevision (Juan Perez took action)
  (3, 2, 2, '2025-05-01 09:00:00', '2025-05-01 09:00:30', NULL), -- Evento 2: AutoDetectado -> Rechazado (no responsable for initial auto-detect)
  (4, 2, 4, '2025-05-01 09:00:30', '2025-05-01 09:01:00', 2),  -- Evento 2: Rechazado (Maria Gomez took action)
  (5, 3, 1, '2025-05-01 10:00:00', '2025-05-01 10:00:30', NULL), -- Evento 3: AutoDetectado -> Rechazado
  (6, 3, 4, '2025-05-01 10:00:30', '2025-05-01 10:01:00', 1);  -- Evento 3: Rechazado (Juan Perez took action)