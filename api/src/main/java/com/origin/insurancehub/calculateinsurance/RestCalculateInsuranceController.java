package com.origin.insurancehub.calculateinsurance;

import com.origin.insurancehub.entities.house.House;
import com.origin.insurancehub.entities.house.OwnershipStatus;
import com.origin.insurancehub.entities.user.MaritalStatus;
import com.origin.insurancehub.entities.vehicle.Vehicle;
import com.origin.insurancehub.usecases.calculateinsurance.CalculateInsuranceInteractor;
import com.origin.insurancehub.usecases.calculateinsurance.CalculateInsuranceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Validated
public class RestCalculateInsuranceController {

    private final CalculateInsuranceProvider provider;

    @PostMapping("insurance/all")
    public ResponseEntity<RestCalculateInsuranceResponse> getAllInsurances(
            @RequestBody @Valid RestCalculateInsuranceRequest request) {
        final CalculateInsuranceRequest useCaseRequest = this.parseRequest(request);

        final RestCalculateInsurancePresenter presenter = provider.getPresenter();
        final CalculateInsuranceInteractor interactor = provider.getInteractor(presenter);

        interactor.execute(useCaseRequest);

        final RestCalculateInsuranceResponse response = presenter.buildPayload();

        return ResponseEntity.ok(response);
    }

    private CalculateInsuranceRequest parseRequest(final RestCalculateInsuranceRequest request) {
        return CalculateInsuranceRequest.builder()
                .house(Optional.ofNullable(request.getHouse()).map(house ->
                                House.builder()
                                        .ownershipStatus(OwnershipStatus.fromLabel(house.getOwnershipStatus()))
                                        .build())
                        .orElse(null)
                )
                .maritalStatus(MaritalStatus.fromLabel(request.getMaritalStatus()))
                .age(request.getAge())
                .dependents(request.getDependents())
                .income(request.getIncome())
                .riskQuestions(request.getRiskQuestions())
                .vehicle(Optional.ofNullable(request.getVehicle()).map(vehicle ->
                                Vehicle.builder().year(vehicle.getYear()).build()
                        ).orElse(null)
                )
                .build();
    }
}
