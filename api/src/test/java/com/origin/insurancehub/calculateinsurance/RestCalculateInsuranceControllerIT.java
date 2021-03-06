package com.origin.insurancehub.calculateinsurance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.origin.insurancehub.entities.insurance.InsurancePlan;
import com.origin.insurancehub.usecases.calculateinsurance.CalculateInsuranceInteractor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert"})
class RestCalculateInsuranceControllerIT {

    private static final String INSURANCE_ENDPOINT = "/insurance/all";

    @MockBean
    private CalculateInsuranceProvider provider;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnSuccessForCorrectPayload() throws Exception {
        final RestCalculateInsurancePresenter presenter = mock(RestCalculateInsurancePresenter.class);
        final CalculateInsuranceInteractor interactor = mock(CalculateInsuranceInteractor.class);
        final RestCalculateInsuranceRequest request = new RestCalculateInsuranceRequest(
                35L, 1L, List.of(new RestCalculateInsuranceRequest.House(1L, "owned")), 0L, "married", List.of(0, 1, 0),
                List.of(new RestCalculateInsuranceRequest.Vehicle(2L, 2018L))
        );
        final RestCalculateInsuranceResponse response = RestCalculateInsuranceResponse.builder()
                .life(InsurancePlan.REGULAR.getLabel())
                .home(List.of(
                        new RestCalculateInsuranceResponse.InsuranceResponseItem(1L,
                                InsurancePlan.ECONOMIC.getLabel())))
                .auto(List.of(
                        new RestCalculateInsuranceResponse.InsuranceResponseItem(1L,
                                InsurancePlan.REGULAR.getLabel())))
                .disability(InsurancePlan.INELIGIBLE.getLabel())
                .build();

        when(this.provider.getPresenter()).thenReturn(presenter);
        when(presenter.buildPayload()).thenReturn(response);
        when(this.provider.getInteractor(presenter)).thenReturn(interactor);

        mockMvc.perform(post(INSURANCE_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnSuccessForHouseOrVehiclesNull() throws Exception {
        final RestCalculateInsurancePresenter presenter = mock(RestCalculateInsurancePresenter.class);
        final CalculateInsuranceInteractor interactor = mock(CalculateInsuranceInteractor.class);
        final RestCalculateInsuranceRequest request = new RestCalculateInsuranceRequest(
                35L, 1L, List.of(), 0L, "married", List.of(0, 1, 0), List.of()
        );
        final RestCalculateInsuranceResponse response = RestCalculateInsuranceResponse.builder()
                .life(InsurancePlan.REGULAR.getLabel())
                .home(List.of())
                .auto(List.of())
                .disability(InsurancePlan.INELIGIBLE.getLabel())
                .build();

        when(this.provider.getPresenter()).thenReturn(presenter);
        when(presenter.buildPayload()).thenReturn(response);
        when(this.provider.getInteractor(presenter)).thenReturn(interactor);

        mockMvc.perform(post(INSURANCE_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWithNonexistentStatus() throws Exception {
        final RestCalculateInsurancePresenter presenter = mock(RestCalculateInsurancePresenter.class);
        final CalculateInsuranceInteractor interactor = mock(CalculateInsuranceInteractor.class);
        final RestCalculateInsuranceRequest request = new RestCalculateInsuranceRequest(
                35L, 1L, List.of(new RestCalculateInsuranceRequest.House(1L, "non-existent-value")), 0L,
                "non-existent-value",
                List.of(0, 1, 0),
                List.of(new RestCalculateInsuranceRequest.Vehicle(2L, 2018L))
        );

        when(this.provider.getPresenter()).thenReturn(presenter);
        when(this.provider.getInteractor(presenter)).thenReturn(interactor);

        mockMvc.perform(post(INSURANCE_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWithNonPositiveValues() throws Exception {
        final RestCalculateInsurancePresenter presenter = mock(RestCalculateInsurancePresenter.class);
        final CalculateInsuranceInteractor interactor = mock(CalculateInsuranceInteractor.class);
        final RestCalculateInsuranceRequest request = new RestCalculateInsuranceRequest(
                -1L, 11L, List.of(new RestCalculateInsuranceRequest.House(1L, "owned")), -1L, "married",
                List.of(0, 1, 0),
                List.of(new RestCalculateInsuranceRequest.Vehicle(2L, -2000L))
        );

        when(this.provider.getPresenter()).thenReturn(presenter);
        when(this.provider.getInteractor(presenter)).thenReturn(interactor);

        mockMvc.perform(post(INSURANCE_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
