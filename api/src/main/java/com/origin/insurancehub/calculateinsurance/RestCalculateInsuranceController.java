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
import java.util.List;
import java.util.stream.Collectors;

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
                .houses(parseHousesList(request.getHouses()))
                .maritalStatus(MaritalStatus.fromLabel(request.getMaritalStatus()))
                .age(request.getAge())
                .dependents(request.getDependents())
                .income(request.getIncome())
                .riskQuestions(request.getRiskQuestions())
                .vehicles(parseVehiclesList(request.getVehicles()))
                .build();
    }

    private List<House> parseHousesList(final List<RestCalculateInsuranceRequest.House> houses) {
        return houses.stream()
                .map(house -> House.builder().id(house.getId())
                        .ownershipStatus(OwnershipStatus.fromLabel(house.getOwnershipStatus())).build()).collect(
                        Collectors.toList());
    }

    private List<Vehicle> parseVehiclesList(final List<RestCalculateInsuranceRequest.Vehicle> vehicles) {
        return vehicles.stream()
                .map(vehicle -> Vehicle.builder().id(vehicle.getId())
                        .year(vehicle.getYear()).build()).collect(
                        Collectors.toList());
    }
}
