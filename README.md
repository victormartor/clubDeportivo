# Sistema de gestión de un club deportivo

Aplicación Java usando el framework Spring que realiza la gestión de un club deportivo. 

## Puesta a punto

Antes de ejecutar la aplicación es necesario realizar una serie de pasos: 

1. Entrar en mysql a través de nuestra consola y crear la base de datos "club".
2. Modificar el archivo *application.yml* con la contraseña del usuario "root" de nuestra base de datos.

Una vez hecho esto ejecutaremos la aplicación para que se creen todas las tablas y sus relaciones en la base de datos. Antes de poder usarla es necesario **rellenar la tabla point** con valores adecuados. Para ello podemos copiar el contenido del archivo *data.sql* situado en (src/main/resources). Este es el contenido:

    insert into point(id, position, puntuation) values(1, 1, 10);
    insert into point(id, position, puntuation) values(2, 2, 8);
    insert into point(id, position, puntuation) values(3, 3, 6);
    insert into point(id, position, puntuation) values(4, 4, 5);
    insert into point(id, position, puntuation) values(5, 5, 4);
    insert into point(id, position, puntuation) values(6, 6, 3);
    insert into point(id, position, puntuation) values(7, 7, 2);
    insert into point(id, position, puntuation) values(8, 8, 1);

Debemos copiar y pegar estas sentencias en nuestra consola para que la tabla *point* tenga valores. Ahora ya podemos usar la aplicación. La url raíz de la aplicación es http://localhost:8081

## Uso de la aplicación

### Club

Para crear un club accedemos a */club* y mediante post insertamos los datos del club de esta manera:

    {
      "name" : "nombreClub"
    }

### Corredor

La entidad *runner* dipone de un CRUD completo. A continuación se detalla su uso:

#### Crear corredor

Para crear un corredor accedemos a */runner* y mediante post insertamos los datos:

    {
      "name":"nombreCorredor",
      "year":2018
    }
    
El campo *year* corresponde al año de nacimiento del corredor.

### Obtener todos los corredores

Para obtener todos los corredores paginados accedemos a */runner* mediante get. A esta url podemos añadirle los siguientes campos:
+ page: el número de la página.
+ size: el número de elementos por página.
+ name: el nombre del corredor por si se quiere buscar por nombre.

### Obtener un corredor mediante su id

Para obtener un corredor mediante su id accedemos a */runner/{idRunner}* mediante get donde la variable *idRunner* es el id del corredor que queremos obtener.

### Actualizar los datos de un corredor

Para actualizar los datos de un corredor accedemos a */runner/{idRunner}* mediante put e insertamos:

    {
      "name":"nombreCorredor",
      "year":2018
    }

### Eliminar corredor

Para eliminar un corredor accedemos a */runner/{idRunner}* mediante delete.

### Actualizar el club al que pertenece el corredor

Para asignarle o actualizar el club al que pertenece un corredor debemos acceder a */runner/{idRunner}/club/{idClub}* mediante put. La variable *idClub* es el id del club que queremos asignarle al corredor.

### Obtener el club al que pertenece un corredor

Para obtener el club al que pertenece un corredor accedemos a */runner/{idRunner}/club* y nos devolverá los datos de su club.

## Prueba
La entidad *trial* dispone de distintas funciones:

### Crear prueba
Para crear una prueba accedemos a */trial* mediante post e insertamos los siguientes datos:

    {
      "name":"nombrePrueba",
      "date":"2018/10/07"
    }
    
Donde *date* es la fecha en que se realiza la prueba. 

> **Nota:** Cuando una prueba es creada **automáticamente se le asigna el sistema de puntuación de la tabla *point***. Se ha implementado así para facilitar el uso de la aplicación, pero está preparada para que distintas pruebas tengan distintos sistemas de puntuación solo que no se ha implementado la función de asignar distintos sistemas de puntuación a distintas pruebas.

### Insertar resultado a una prueba

Para insertar un resultado a una prueba accedemos a */trial/{idTrial}/result* mediante post e insertamos los siguientes datos:

    {
      "id_runner":1,
      "seconds":150
    }

Este resultado contiene el id del corredor que ha disputado la prueba y los segundos que ha tardado en completarla.

### Obtener los resultados de una prueba sin ordenar

Para obtener todos los resultados de una prueba sin ningún tipo de ordenación accedemos a */trial/{idTrial}/result* mediante get.

# Querys complejas
### Obtener la clasificación master 40

Para obtener la clasificación de los corredores de la categoría master 40 ordenados según los segundos debemos acceder a */trial/{idTrial}/master40*. Esto nos devolverá una lista de los corredores mayores de 40 años que disputaron la prueba ordenados.

### Obtener la clasificación master 30

Para obtener la clasificación de los corredores de la categoría master 30 ordenados según los segundos debemos acceder a */trial/{idTrial}/master30*. Esto nos devolverá una lista de los corredores de entre 40 y 30 años que disputaron la prueba ordenados.

### Obtener la clasificación master 20

Para obtener la clasificación de los corredores de la categoría master 20 ordenados según los segundos debemos acceder a */trial/{idTrial}/master20*. Esto nos devolverá una lista de los corredores de entre 30 y 20 años que disputaron la prueba ordenados.

# Algoritmo complejo
Para la realización del algoritmo complejo se pidió obtener la clasificación de los clubs por puntos. Estos puntos son el resultado de la suma de puntos obtenidos por cada corredor en una prueba según el sistema de puntuaje de dicha prueba. Se ha realizado de dos formas diferentes:

+ **Sin tener en cuenta las categorías:** los puntos se han repartido a los corredores sin tener en cuenta su categoría. El primero de la carrera ha obtenido elmáximo de puntos y así hacia abajo.
+ **Teniendo en cuenta las categorías:** los puntos se han repartido a los corredores segun su categoría. Para categoría se ha realizado el reparto de puntos correspondientes. El primero de cada categoría obtiene el máximo de puntos y así hacia abajo.

Los dos algoritmos guardan la información en un fichero llamado *clubs.txt* que se sitúa en la raíz del proyecto.

### Obtener la clasificación de los clubs por puntos sin tener en cuenta las categorías

Para obtener la clasificación de los clubs sin tener en cuenta las categorías accedemos a */trial/{idTrial}clubClasi* mediante get.

### Obtener la clasificación de los clubs por puntos teniendo en cuenta las categorías

Para obtener la clasificación de los clubs teniendo en cuenta las categorías accedemos a */trial/{idTrial}clubClasiCat* mediante get.
