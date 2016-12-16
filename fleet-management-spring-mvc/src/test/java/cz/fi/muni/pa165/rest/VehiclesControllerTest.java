package cz.fi.muni.pa165.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.fi.muni.pa165.dto.VehicleCreateDTO;
import cz.fi.muni.pa165.dto.VehicleDTO;
import cz.fi.muni.pa165.facade.VehicleFacade;
import cz.fi.muni.pa165.mvc.config.MySpringMvcConfig;
import cz.fi.muni.pa165.rest.controllers.GlobalExceptionController;
import cz.fi.muni.pa165.rest.controllers.VehiclesController;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@WebAppConfiguration
@ContextConfiguration(classes = {MySpringMvcConfig.class})
public class VehiclesControllerTest extends AbstractTestNGSpringContextTests {

    @Mock
    private VehicleFacade vehicleFacade;

    @Autowired
    @InjectMocks
    private VehiclesController vehiclesController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(vehiclesController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }


    /**
     * Registering the GlobalExceptionController if @ControllerAdvice is used
     * this can be used in SetHandlerExceptionResolvers() standaloneSetup()
     * Note that new Spring version from 4.2 has already a setControllerAdvice() method on
     * MockMVC builders, so in that case it is only needed to pass one or more
     *
     * @return
     * @ControllerAdvice(s) configured within the application
     */
    private ExceptionHandlerExceptionResolver createExceptionResolver() {
        ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
            protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod, Exception exception) {
                Method method = new ExceptionHandlerMethodResolver(GlobalExceptionController.class).resolveMethod(exception);
                return new ServletInvocableHandlerMethod(new GlobalExceptionController(), method);
            }
        };
        exceptionResolver.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        exceptionResolver.afterPropertiesSet();
        return exceptionResolver;
    }

    @Test
    public void debugTest() throws Exception {
        doReturn(Collections.unmodifiableList(this.createVehicles())).when(
                vehicleFacade).getAllVehicles();
        mockMvc.perform(get("/rest/vehicles"));//.andDo(print());
    }

    @Test
    public void getAllVehicles() throws Exception {

        doReturn(Collections.unmodifiableList(this.createVehicles())).when(
                vehicleFacade).getAllVehicles();

        mockMvc.perform(get("/rest/vehicles"))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_VALUE))
                /*.andExpect(
                        jsonPath("$.[?(@.id==10)].type").value("Ford"))
                .andExpect(jsonPath("$.[?(@.id==20)].type").value("Skoda"))*/;

    }

    @Test
    public void getValidVehicle() throws Exception {

        List<VehicleDTO> vehicles = this.createVehicles();

        doReturn(vehicles.get(0)).when(vehicleFacade).findVehicleById(10l);
        doReturn(vehicles.get(1)).when(vehicleFacade).findVehicleById(20l);

        mockMvc.perform(get("/rest/vehicles/10"))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("Ford"));
        mockMvc.perform(get("/rest/vehicles/20"))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.type").value("Skoda"));

    }

    @Test
    public void getInvalidVehicle() throws Exception {
        doReturn(null).when(vehicleFacade).findVehicleById(1l);

        mockMvc.perform(get("/rest/vehicles/1")).andExpect(
                status().is4xxClientError());

    }


    @Test
    public void createVehicle() throws Exception {

        VehicleDTO vehicleCreateDTO = new VehicleDTO();
        vehicleCreateDTO.setType("Ford");

        doReturn(1l).when(vehicleFacade).addNewVehicle(
                any(VehicleCreateDTO.class));

        String json = this.convertObjectToJsonBytes(vehicleCreateDTO);

        System.out.println(json);

        mockMvc.perform(
                post("/rest/vehicles/create").contentType(MediaType.APPLICATION_JSON)
                        .content(json))//.andDo(print())
                .andExpect(status().isOk());
    }


    private List<VehicleDTO> createVehicles() {
        VehicleDTO vehicleOne = new VehicleDTO();
        //vehicleOne.setId(10L);
        vehicleOne.setType("Ford");
        vehicleOne.setEngineType("diesel");
        vehicleOne.setProductionYear(2000);
        vehicleOne.setInitialKilometrage(0L);
        vehicleOne.setVrp("ABC1011");
        vehicleOne.setVin("x16565195");


        VehicleDTO vehicleTwo = new VehicleDTO();
        //vehicleTwo.setId(20L);
        vehicleTwo.setType("Skoda");
        vehicleTwo.setEngineType("diesel");
        vehicleTwo.setProductionYear(2005);
        vehicleTwo.setInitialKilometrage(10L);
        vehicleTwo.setVrp("AAA1111");
        vehicleTwo.setVin("A115165");


        return Arrays.asList(vehicleOne, vehicleTwo);
    }

    private static String convertObjectToJsonBytes(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }
}
