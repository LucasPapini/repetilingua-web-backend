package br.com.lucaspapini.repetilingua.controllers.docs;

import br.com.lucaspapini.repetilingua.entities.dto.TextPartCreateRequestDTO;
import br.com.lucaspapini.repetilingua.entities.dto.TextPartListDTO;
import br.com.lucaspapini.repetilingua.entities.dto.TextPartResponseDTO;
import br.com.lucaspapini.repetilingua.entities.dto.TextPartUpdateRequestDTO;
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

public interface TextPartControllerDocs {
    @Operation(
            summary = "Encontre todas as Parte dos Textos",
            description = "Esse metodos listara todas as Parte do Texto Cadastrados OBS: não esta filtando por usuário ...",
            tags = {"Partes dos Textos"},
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
                                                    schema = @Schema(implementation = TextPartListDTO.class)
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
    public ResponseEntity<Page<TextPartListDTO>> findAll(Pageable pageable);

    @Operation(
            summary = "Encontre Parte do Texto Por ID",
            description = "Esse metodos listar as Parte do Texto Cadastrados por ID",
            tags = {"Partes dos Textos"},
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
                                                    schema = @Schema(implementation = TextPartResponseDTO.class)
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
    public ResponseEntity<TextPartResponseDTO> findById(@PathVariable("id") Long id);


    @Operation(summary = "Adicionar uma nova Parte de Texto",
            description = "Adicionar uma nova Parte de Texto inserido atraves de um JSON",
            tags = {"Partes dos Textos"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TextPartCreateRequestDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<TextPartCreateRequestDTO> create(@RequestBody TextPartCreateRequestDTO dto);

    @Operation(summary = "Atualizar uma Parte do Texto",
            description = "Atualizar uma Parte do Texto inserido atraves de um JSON",
            tags = {"Partes dos Textos"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TextPartCreateRequestDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<TextPartUpdateRequestDTO> update(
    @PathVariable("id") Long id,
    @RequestBody TextPartUpdateRequestDTO dto
    );

    @Operation(summary = "Delete uma Parte do Texto",
            description = "Delete uma Parte do Texto inserido um ID da parte que deseja deletar",
            tags = {"Partes dos Textos"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TextPartCreateRequestDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    );
}
