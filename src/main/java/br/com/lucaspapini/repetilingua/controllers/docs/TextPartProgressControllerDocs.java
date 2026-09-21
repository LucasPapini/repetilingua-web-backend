package br.com.lucaspapini.repetilingua.controllers.docs;


import br.com.lucaspapini.repetilingua.entities.dto.TextPartProgresstCreateDTO;
import br.com.lucaspapini.repetilingua.entities.dto.TextPartProgresstDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface TextPartProgressControllerDocs {
    @Operation(
            summary = "Encontre todas os Progressos dos Textos",
            description = "Esse metodos listara todos os Progresso e dos Texto Cadastrados OBS: não esta filtando por usuário ...",
            tags = {"Progressos dos Textos"},
            parameters = {
                    @Parameter(name = "page", description = "Número da página (começa em 0)", example = "0"),
                    @Parameter(name = "size", description = "Quantidade de itens por página", example = "10"),
            },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = TextPartProgresstDTO.class)
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<Page<TextPartProgresstDTO>> findeAll(Pageable pageable);

    @Operation(
            summary = "Encontre todas os Progressos dos Textos",
            description = "Esse metodo retor o progrersso de um Texto pelo id do progresso e id do texto ",
            tags = {"Progressos dos Textos"},
            parameters = {
                    @Parameter(name = "page", description = "Número da página (começa em 0)", example = "0"),
                    @Parameter(name = "size", description = "Quantidade de itens por página", example = "10"),
            },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = TextPartProgresstDTO.class)
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<TextPartProgresstDTO> findByIdAndIdPartText(@PathVariable("id") Long id, @PathVariable("idTextPart") Long idTextPart );

    @Operation(summary = "Adicionar uma novo Progresso do Texto",
            description = "Adicionar uma nova Parte de Texto inserido atraves de um JSON",
            tags = {"Progressos dos Textos"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TextPartProgresstCreateDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<TextPartProgresstCreateDTO> update(@RequestBody TextPartProgresstCreateDTO dto);
}
