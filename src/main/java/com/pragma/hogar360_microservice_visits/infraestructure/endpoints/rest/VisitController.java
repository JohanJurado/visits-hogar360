package com.pragma.hogar360_microservice_visits.infraestructure.endpoints.rest;

import com.pragma.hogar360_microservice_visits.application.dtos.request.VisitRequest;
import com.pragma.hogar360_microservice_visits.application.dtos.response.SaveResponse;
import com.pragma.hogar360_microservice_visits.application.services.IVisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/visit")
@Tag(name = "Visit", description = "API to manage visits")
public class VisitController {

    private final IVisitService visitService;

    @Operation(summary = "Add Visit", description = "Add Visit")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Visit save Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SaveResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid Visit Params",
                    content = @Content
            )
    })
    @PostMapping("/")
    public ResponseEntity<SaveResponse> login(@RequestBody VisitRequest visitRequest){
        return ResponseEntity.status(HttpStatus.OK).body(visitService.save(visitRequest));
    }
}
