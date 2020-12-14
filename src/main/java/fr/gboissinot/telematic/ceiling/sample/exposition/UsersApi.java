package fr.gboissinot.telematic.ceiling.sample.exposition;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

@Api(value = "User API", description = "Operations to User Online API")
interface UsersApi {

    @ApiOperation(value = "Modify gauge")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully gauge modified"),
            @ApiResponse(code = 400, message = "Bad Parameters")
    })
    ResponseEntity<Void> changeGaugeValue(long userId, ModifyUserGaugeRequest modifyClientGaugeRequest);

    @ApiOperation(value = "Reset gauge")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully gauge reset"),
            @ApiResponse(code = 400, message = "Bad Parameters")
    })
    ResponseEntity<Void> resetGaugeValue(long userId);
}
