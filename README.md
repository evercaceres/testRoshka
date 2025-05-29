# API de Búsqueda de Noticias ABC Color
Descripción
API REST que permite buscar noticias en el sitio ABC Color (Paraguay) y devuelve los resultados en formato JSON. Incluye autenticación mediante API Key y opción para obtener imágenes en Base64.

🛠 Requisitos
Para ejecutar la aplicación necesitas:

Java JDK 17


Maven 3.8.6+

🚀 Ejecución de la aplicación

1. Localmente con IDE (VSCode/Eclipse/IntelliJ)
Abre el proyecto en tu IDE

Busca la clase principal ApiController.java

Ejecuta con F5 (Debug) o el botón de ejecución

2. Con Maven
   
bash

mvn spring-boot:run

🔐 Autenticación

La API requiere un API Key válido en el header:


http
GET /consulta?q=paraguay HTTP/1.1
Host: localhost:8080
X-API-KEY: roshka1234

API Keys válidas (configurables en application.properties):


roshka1234,roshkatest, test2025

📚 Documentación API

Accede a la documentación interactiva con Swagger UI:

http://localhost:8080/swagger-ui.html

Endpoint principal:

GET /consulta
Parámetros:

Parámetro	Tipo	Requerido	Descripción

q	String	Sí	Texto de búsqueda

f	Boolean	No	Si es true, incluye imágenes codificadas en Base64 (default: false)

# Ejemplo de respuesta

Solicitud:

http
GET /consulta?q=deportes&f=true HTTP/1.1
Host: localhost:8080
X-API-KEY: roshka1234
Accept: application/json

Respuesta:




[

    {
        "titulo": "Cerro Porteño busca su primer triunfo en la altura de La Paz",
        "enlace": "https://www.abc.com.py/deportes/cerro-porteno",
        "fecha": "6 de junio de 2023",
        "resumen": "El equipo azulgrana enfrentará a Always Ready...",
        "enlace_foto": "https://www.abc.com.py/img/cerro.jpg",
        "contenido_foto": "/9j/4AAQSkZJRgABAQE...",
        "content_type_foto": "image/jpeg"
    },
    
    {
        "titulo": "El gol de Enciso al City es el mejor de la temporada",
        "enlace": "https://www.abc.com.py/deportes/enciso-gol",
        "fecha": "4 de junio de 2023",
        "resumen": "El paraguayo Julio Enciso anotó un gol espectacular...",
        "enlace_foto": "https://www.abc.com.py/img/enciso.jpg"
    }
    
]

- Solución de problemas

Error 401 - API Key inválida



{

    "codigo": "g401",
    "error": "API Key inválida o faltante",
    "sugerencia": "Incluye un header X-API-KEY válido"
}

Error 404 - No se encontraron noticias



{

    "codigo": "g267",
    "error": "No se encontraron noticias para el texto: deportes",
    "sugerencia": "Intenta con otros términos de búsqueda"
}
