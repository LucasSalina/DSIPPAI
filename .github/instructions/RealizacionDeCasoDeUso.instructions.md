---
applyTo: '**'
---
Esta es una realizacion de un caso de uso en la cual esta basado el programa de la aplicacion. El caso de uso es el siguiente:
---
Objetivo: Registrar la aprobación, rechazo o derivación de un sismo registrado automáticamente.
Flujo: Registro de resultado de revisión manual sin modificación de datos del evento sísmico, confirmándolo y notificando
5. actor: selecciona la opción “Registrar resultado de revisión manual”.
6. Sistema: busca todos los eventos sísmicos auto detectados que aún no han sido revisados y encuentra al menos uno. Los
ordena por fecha y hora de ocurrencia y visualiza de cada uno los datos principales: fecha y hora de ocurrencia del evento,
ubicación (coordenadas geográficas del epicentro y del hipocentro), magnitud, solicitando la selección de uno de ellos.
7. actor: selecciona un evento sísmico.
8. Sistema: bloquea el evento seleccionado cambiando su estado a bloqueado en revisión.
9. Sistema: Busca los datos sísmicos registrados para el evento sísmico seleccionado, lo cual incluye:
9.1. Obtener y mostrar el alcance, clasificación y el origen de generación del evento sísmico.
9.2. Recorrer las series temporales asociadas a ese evento y las respectivas muestras, obteniendo para cada instante de
tiempo los valores alcanzados de velocidad de onda, frecuencia de onda y longitud, clasificando esta información por
estación sismológica.
9.3. Llamar al caso de uso Generar Sismograma para generar y visualizar un sismograma por estación sismológica y el mismo
se ejecuta con éxito.
10. Sistema: habilita la opción para visualizar en un mapa el evento sísmico y las estaciones sismológicas involucradas
11. actor: no desea visualizar el mapa.
12. Sistema: permite la modificación de los siguientes datos del evento sísmico: magnitud, alcance y origen de generación
13. actor: no desea modificar los datos del evento sísmico.
14. Sistema: solicita que se seleccione una acción a través de las siguientes opciones: Confirmar evento, Rechazar evento o
Solicitar revisión a experto.
15. actor: selecciona la opción Rechazar evento.
16. Sistema: valida que exista magnitud, alcance y origen de generación del evento y que se haya seleccionado una acción y es
correcto.
17. Sistema: actualiza el estado del evento sísmico a rechazado, registrando la fecha y hora actual como fecha de revisión y el
actor logueado como responsable de la misma. Fin CU.