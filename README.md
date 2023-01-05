# equipmentControlSystem

Proyecto para el control de mantenimiento de equipos (Backend).

# Tecnologias
Este proyecto está desarrollado con Sping Boot, como manejador del ciclo de vida de proyecto tenemos a gredle, también usamos para generación de los reportes jaserreport y Apache POI para el Excel bitácora de los mantenimientos, se realizan los mapeos automáticos con mapstruct, la base de datos usada es PostgreSQL y para su despliegue se está usando docker, expuesto mediante traefik, para el control de sesiones se usa el spring security en su forma basica con el manejo de roles.

# Negocio

Este proyecto busca sustituir el manejo manual que tiene la empresa del llenado de la bitácora de mantenimientos a las escuelas del sector de chicago estados unidos ya, se busca tener un control de los usuarios que llenan o modifican dicha información, adicional se facilita la generación de las Etiquetas de embalaje de los Pc que se encuentran en los estados de para reciclar o en espera de piezas para su mantenimiento, con este software se suple esta necesidad operativa y se espera que con el tiempo pueda suplir otras operaciones manuales del tipo administrativo que se realizan basados con la información generada desde los mantenimientos como costos, cotizaciones y órdenes de compra.
