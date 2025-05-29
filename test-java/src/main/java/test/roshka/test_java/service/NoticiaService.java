package test.roshka.test_java.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import test.roshka.test_java.model.NoticiaNews;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticiaService {

    @Value("${app.user.agent:Mozilla/5.0}")
    private String userAgent;

    public List<NoticiaNews> buscarNoticias(String query, boolean incluirFoto) {
        List<NoticiaNews> resultados = new ArrayList<>();

        try {
            String urlBusqueda = "https://www.abc.com.py/buscar/?q=" + 
                               URLEncoder.encode(query, StandardCharsets.UTF_8);

            Document doc = Jsoup.connect(urlBusqueda)
                    .userAgent(userAgent)
                    .timeout(10000)
                    .get();

            Elements articulos = doc.select("article.noticia, div.noticia-item");

            for (Element articulo : articulos) {
                NoticiaNews noticia = new NoticiaNews();
                
                noticia.titulo = articulo.select("h2, h3").text();
                noticia.enlace = articulo.select("a").attr("abs:href");
                noticia.fecha = articulo.select(".fecha, time").text();
                noticia.resumen = articulo.select(".resumen, p").text();
                noticia.enlace_foto = articulo.select("img").attr("abs:src");

                if (incluirFoto && noticia.enlace_foto != null) {
                    procesarImagen(noticia);
                }

                resultados.add(noticia);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al obtener noticias: " + e.getMessage(), e);
        }

        return resultados;
    }

    private void procesarImagen(NoticiaNews noticia) {
        try {
            byte[] imagenBytes = Jsoup.connect(noticia.enlace_foto)
                    .ignoreContentType(true)
                    .execute()
                    .bodyAsBytes();
            
            noticia.contenido_foto = java.util.Base64.getEncoder().encodeToString(imagenBytes);
            noticia.content_type_foto = "image/jpeg";
        } catch (Exception e) {
            System.err.println("Error al procesar imagen: " + e.getMessage());
        }
    }
}