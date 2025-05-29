package test.roshka.test_java.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import test.roshka.test_java.exception.NoticiaException;
import test.roshka.test_java.model.NoticiaNews;
import test.roshka.test_java.service.NoticiaService;
import test.roshka.test_java.service.ApiKeyService;
import java.util.Map;

@Tag(name = "Consulta de Noticias", description = "Permite consultar noticias desde ABC")
@RestController
@RequestMapping("/consulta")
public class ApiController {

    private final NoticiaService noticiasService;
    private final ApiKeyService apiKeyService;


    public ApiController(NoticiaService noticiasService, ApiKeyService apiKeyService) {
        this.noticiasService = noticiasService;
        this.apiKeyService = apiKeyService;
    }

    @Operation(summary = "Buscar noticias", description = "Retorna una lista de noticias para el texto buscado")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
                            MediaType.TEXT_PLAIN_VALUE, MediaType.TEXT_HTML_VALUE})
    public ResponseEntity<?> consultarNoticias(
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "f", required = false, defaultValue = "false") boolean incluirFoto,
            @RequestHeader(value = "x-api-key", required = false) String apiKey) {
        
                if (!apiKeyService.isValidApiKey(apiKey)) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(Map.of(
                                "codigo", "g401",
                                "error", "API Key inválida o faltante",
                                "sugerencia", "Incluye un header X-API-KEY válido"
                            ));
                }

        if (query == null || query.trim().isEmpty()) {
            throw new NoticiaException("g268", "El parámetro 'q' es requerido", HttpStatus.BAD_REQUEST);
        }

        try {
            List<NoticiaNews> noticias = noticiasService.buscarNoticias(query, incluirFoto);

            if (noticias.isEmpty()) {
                throw new NoticiaException("g267", 
                    "No se encontraron noticias. La estructura de ABC Color pudo haber cambiado.", 
                    HttpStatus.NOT_FOUND);
            }


            return ResponseEntity.ok(noticias);

        } catch (Exception e) {
            throw new NoticiaException("g500", 
                "Error al procesar la solicitud: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}