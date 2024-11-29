package br.com.soliva.userserviceapi.controller;

import br.com.soliva.userserviceapi.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import models.exceptions.StandardError;
import models.requests.CreateUserRequest;
import models.responses.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "UserController", description = "Controller responsible for user operations")
@RequestMapping("/api/users")
public interface UserController {

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User found"),
            @ApiResponse(
                    responseCode = "404", description = "User not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(
                    responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,schema = @Schema(implementation = StandardError.class)))
    })
    @Operation(summary = "Find user by id")
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(
            @Parameter(description = "User id", required = true, example = "673b36f6202c480843aac88a")
            @PathVariable(name = "id") final String id);

    @Operation(summary = "Save new user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "User created"),
            @ApiResponse(
                    responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(
                    responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = StandardError.class)))
    })
    @PostMapping
    ResponseEntity<Void> save(@Valid @RequestBody final CreateUserRequest createUserRequest);

    @Operation(summary = "Find all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = UserResponse.class))
                    )),
            @ApiResponse(
                    responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,schema = @Schema(implementation = StandardError.class)
                    ))
    })
    @GetMapping
    ResponseEntity<List<UserResponse>> findAll();
}