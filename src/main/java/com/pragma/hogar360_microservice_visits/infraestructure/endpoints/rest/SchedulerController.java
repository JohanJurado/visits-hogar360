package com.pragma.hogar360_microservice_visits.infraestructure.endpoints.rest;

import com.pragma.hogar360_microservice_visits.application.dtos.request.SchedulerRequest;
import com.pragma.hogar360_microservice_visits.application.dtos.response.SaveResponse;
import com.pragma.hogar360_microservice_visits.application.services.ISchedulerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pragma.hogar360_microservice_visits.infraestructure.utils.constants.InfrastructureConstants.HAS_ROLE_SELLER;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/scheduler")
@Tag(name = "Scheduler", description = "API to manage schedulers")
public class SchedulerController {

    private final ISchedulerService schedulerService;

    @Operation(summary = "Add Scheduler", description = "Seller add a new Scheduler")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Scheduler save Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SaveResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid Scheduler",
                    content = @Content
            )
    })
    @PostMapping("/")
    @PreAuthorize(HAS_ROLE_SELLER)
    public ResponseEntity<SaveResponse> login(@RequestBody SchedulerRequest schedulerRequest){
        return ResponseEntity.status(HttpStatus.OK).body(schedulerService.save(schedulerRequest));
    }
}

