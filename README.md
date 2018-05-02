# Ejemplo aplicación web Java EE 7 con PrimeFaces y Maven

Proyecto Maven que muestra una aplicación web usando PrimeFaces sobre Java EE 7. [PrimeFaces](https://www.primefaces.org/) es un conjunto de componentes para construir aplicaciones web. Este ejemplo muestra también como incluir plantillas en las pantallas JSF.

## Contenido

Este es un proyecto Maven. Esto quiere decir que se tiene un archivo `pom.xml` que contiene una definición del proyecto.

Además, como proyecto Maven, se tienen carpetas separadas para el código fuente que se debe incluir en los archivos `.jar` y para las pruebas

| Carpeta        | Descripción                |
| :------------- | :------------------------- |
| `src/main/java`    | Clases principales en Java        |
| `src/main/webapp`  | Archivos de la aplicación web     |

El proyecto contiene unas clases y archivos de interés:

| en el `src/main/java`         | Descripción                        |
| :-------------------------------- | :--------------------------------- |
| `ejemplo.web.presentacion.HolaMundo.java`        | Modelo (datos) observable desde las pantallas y que se conecta al servicio |

| en el `src/main/webapp`         | Descripción                        |
| :-------------------------------- | :--------------------------------- |
| `index.xhtml`          | Página (pantalla) de prueba |
| `WEB-INF/plantilla/plantilla.xhtml` | Plantilla usada por la aplicación |
| `WEB-INF/beans.xml`  | Archivo de configuración que indica al servidor a buscar las clases con anotaciones |
| `WEB-INF/web.xml`     | Archivo de configuración que define la configuración de JSF y la extensión de las pantallas | 


## Pantallas usando `JSF`

En este proyecto, las pantallas son construídas usando la tecnología de Java Server Faces (JSF), el esquema de plantillas Facelets y los componentes de PrimeFaces.

En el archivo `web.xml` se define la extensión de los archivos usados en la pantalla

```
    <servlet-mapping>
        <servlet-name>Faces Servlet</servlet-name>
        <url-pattern>*.xhtml</url-pattern>
    </servlet-mapping>
    <welcome-file-list>
        <welcome-file>index.xhtml</welcome-file>
    </welcome-file-list>    
```

El archivo `WEB-INF/plantilla/plantilla.xhtml` define una plantilla para las pantallas.

```
<!-- La plantilla define tres secciones -->

	<!-- Encabezado -->
	<ui:insert name="encabezado"> ... </ui:insert>
	
	<!-- Contenido -->
	<ui:insert name="contenido"> ... </ui:insert>
	
	<!-- Pié de página -->
	<ui:insert name="pie-pagina"> ... </ui:insert>

```

El archivo `index.xhtml` incluye una referencia a esta plantilla y define el contenido para dos de las secciones.

```
<!-- Usa la plantilla -->
<ui:composition template="/WEB-INF/plantilla/plantilla.xhtml">

	<!-- define el contenido para el encabezado -->
	<ui:define name="encabezado">
		:
	</ui:define>
	
	<!-- define el contenido para el contenido -->
	<ui:define name="contenido">
		:
	</ui:define>
		
</ui:composition>
```

Este `index.xhtml` utiliza los elementos del modelo que expone la aplicación Java. En este caso, se usa `holaMundo` para referenciar a la clase `HolaMundo`. La clase tiene dos atributos `nombre`, `apellido`, y un método `mostrarSaludo()`. La página tiene campos de texto donde el usuario puede ingresar la información de nombre y apellido.

```
<h:form>

	<h:outputText value="Nombre: " />
	<p:inputText value="#{holaMundo.nombre}" />

	<h:outputText value="Apellido: " />
	<p:inputText value="#{holaMundo.apellido}" />
```
			

El formulario tiene un botón que, al hacer clic en él, muestra una caja de diálogo con el saludo.

```
	<!-- Al hacer click, mostrar 'dialogoSaludo' -->
	<p:commandButton value="Enviar" update="saludo"
		oncomplete="PF('dialogoSaludo').show()" />
</h:form>

<!--  Caja de diálogo 'dialogoSaludo' -->
<p:dialog header="Saludo" widgetVar="dialogoSaludo" modal="true"
	resizable="false">
	<h:panelGrid id="saludo" columns="1" cellpadding="4">
		<h:outputText value="#{holaMundo.mostrarSaludo()}" />
	</h:panelGrid>
</p:dialog>			
```


## Configuración en el `pom.xml`

En el `pom.xml` se tiene una dependencia a las librerías de Java EE.

```
<!--  Java EE 7 -->
<dependency>
	<groupId>javax</groupId>
	<artifactId>javaee-api</artifactId>
	<version>7.0</version>
	<scope>provided</scope>
</dependency>
```

y a los componentes de PrimeFaces.

```
<!-- Primefaces -->
<dependency>
	<groupId>org.primefaces</groupId>
	<artifactId>primefaces</artifactId>
	<version>6.2</version>
</dependency>  
```

Con el fin de poder ejecutar el proyecto sin tener pre-instalado un servidor de aplicaciones, el `pom.xml` incluye un plugin de wildfly.

```
<!-- para ejecutar la aplicación en Wildfly -->
<plugin>
	<groupId>org.wildfly.plugins</groupId>
	<artifactId>wildfly-maven-plugin</artifactId>
	<version>1.2.1.Final</version>
</plugin> 
```


## Ejecución

El proyecto es una aplicación web con una sola pantalla. Es posible ejecutar la aplicación desde la línea de comandos usando Maven.

```bash
mvn wildfly:run
```

NOTA: Este comando descarga un servidor de aplicaciones (+150MB) durante la primera ejecución.

Si usa Eclipse, es posible instalar un servidor de aplicaciones y ejecutar el proyecto haciendo click-derecho sobre el proyecto y seleccionando `Run As > Run on Server`.  La aplicación debe ser visible en `http://localhost:8080/ejemplo-web-primefaces/`

