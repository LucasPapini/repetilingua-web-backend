package br.com.lucaspapini.repetilingua.controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/audios")
public class AudioController {
    private final Path rootLocation = Paths.get("audios");

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping("/**")
    public ResponseEntity<Resource> streamAudio(HttpServletRequest request) {
        try {
            // Remove o prefixo "/audios/" para pegar o caminho relativo do arquivo
            String requestUri = request.getRequestURI();
            String relativePath = requestUri.substring(requestUri.indexOf("/audios/") + 8);

            Path filePath = rootLocation.resolve(relativePath).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            System.out.println("🔍 Buscando arquivo de áudio no disco em: " + filePath);

            if (!resource.exists() || !resource.isReadable()) {
                System.err.println("❌ Arquivo não encontrado ou sem permissão de leitura: " + filePath);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            if (contentType == null) {
                contentType = "audio/mpeg";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
