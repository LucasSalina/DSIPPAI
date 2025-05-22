-- -- Datos maestras
-- INSERT INTO alcane_sismo (descripcion,nombre) VALUES
--   ('Local, bajo 50 km','Local'),
--   ('Regional, 50–200 km','Regional'),
--   ('Global, > 200 km','Global');

-- INSERT INTO clasificacion_sismo (km_profundidad_desde,km_profundidad_hasta,nombre) VALUES
--   (0,70,'Superficial'),
--   (70,300,'Intermedio'),
--   (300,700,'Profundo');

-- INSERT INTO origen_de_generacion (descripcion,nombre) VALUES
--   ('Tectónico','Tectónico'),
--   ('Volcánico','Volcánico'),
--   ('Artificial','Artificial');

-- INSERT INTO estado (ambito,nombre_estado) VALUES
--   ('Sistema','Autodetectado'),
--   ('Revisión','Pendiente'),
--   ('Revisión','Rechazado'),
--   ('Revisión','Aprobado');

-- INSERT INTO tipo_de_dato (denominacion,nombre_unidad_medida,valor_umbral) VALUES
--   ('Aceleración','g',0.01),
--   ('Velocidad','m/s',0.001),
--   ('Desplazamiento','mm',0.1);

-- INSERT INTO estacion_sismologica (codigo_estacion,documento_cert_adq,fecha_solicitud_cert,latitud,longitud,nombre,nro_cert_adquisicion) VALUES
--   ('EST-001','DOC-1001','2023-01-15',40.41,-3.70,'Madrid Centro','CERT-2001'),
--   ('EST-002','DOC-1002','2023-02-20',41.38,2.17,'Barcelona','CERT-2002'),
--   ('EST-003','DOC-1003','2023-03-05',37.98,-1.13,'Alicante','CERT-2003');

-- -- Detalles de muestra
-- INSERT INTO detalle_muestra_sismica (id_tipo,valor) VALUES
--   (1,0.015),(1,0.020),(2,0.003),(3,0.120),(2,0.005);

-- -- Muestras
-- INSERT INTO muestra_sismica (fecha_hora_muestra,id_detalle_muestra) VALUES
--   ('2025-05-01 08:00:00',1),
--   ('2025-05-01 08:00:01',2),
--   ('2025-05-01 08:00:02',3),
--   ('2025-05-01 08:00:03',4),
--   ('2025-05-01 08:00:04',5);

-- Sismógrafos
INSERT INTO sismografo (identificador,id_adquisicion,nro_serie,codigo_estacion) VALUES
  ('SISMO-A',101,'SN-A001','EST-001'),
  ('SISMO-B',102,'SN-B001','EST-002'),
  ('SISMO-C',103,'SN-C001','EST-003');

-- -- Series temporales
-- INSERT INTO serie_temporal (condicion_alarma,fecha_hora_inicio_reg_muestreo,fecha_hora_registros,frecuencia_muestreo,id_muestra_sismica,id_sismografo) VALUES
--   ('OK','2025-05-01 08:00:00','2025-05-01 08:00:10',100.0,1,'SISMO-A'),
--   ('ALARMA','2025-05-01 09:00:00','2025-05-01 09:00:10',50.0,2,'SISMO-B'),
--   ('OK','2025-05-01 10:00:00','2025-05-01 10:00:10',200.0,3,'SISMO-C');

-- -- Eventos sísmicos
-- INSERT INTO evento_sismico (fecha_hora_fin,fecha_hora_ocurrencia,latitud_epicentro,longitud_epicentro,latitud_hipocentro,longitud_hipocentro,valor_magnitud,id_alcance,id_clasificacion,id_estado_actual,id_serie) VALUES
--   ('2025-05-01 08:01:00','2025-05-01 08:00:00',40.42,-3.69,39.90,-3.50,4.5,1,1,1,1),
--   ('2025-05-01 09:01:00','2025-05-01 09:00:00',41.39,2.16,40.00,1.50,5.0,2,2,2,2),
--   ('2025-05-01 10:01:00','2025-05-01 10:00:00',37.99,-1.12,36.50,-1.00,3.8,3,3,1,3);

-- -- Cambios de estado
-- INSERT INTO cambio_estado (id_evento,id_estado,fecha_hora_inicio,fecha_hora_fin,es_estado_actual,sos_autodetectado,sos_pendiente_revision) VALUES
--   (1,1,'2025-05-01 08:00:00','2025-05-01 08:00:30',0,1,0),
--   (1,2,'2025-05-01 08:00:30','2025-05-01 08:01:00',1,0,1),
--   (2,2,'2025-05-01 09:00:00','2025-05-01 09:00:30',0,1,0),
--   (2,4,'2025-05-01 09:00:30','2025-05-01 09:01:00',1,0,0),
--   (3,1,'2025-05-01 10:00:00','2025-05-01 10:00:30',0,1,0),
--   (3,4,'2025-05-01 10:00:30','2025-05-01 10:01:00',1,0,0);
