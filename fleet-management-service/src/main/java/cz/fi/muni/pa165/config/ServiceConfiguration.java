package cz.fi.muni.pa165.config;

import cz.fi.muni.pa165.InMemoryDatabaseContext;
import cz.fi.muni.pa165.dto.*;
import cz.fi.muni.pa165.entity.*;
import cz.fi.muni.pa165.facade.JourneyFacadeImpl;
import cz.fi.muni.pa165.service.JourneyServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.classmap.RelationshipType;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.dozer.loader.api.FieldsMappingOptions.collectionStrategy;

/**
 * @author Martin Schmidt
 */
@Configuration
@Import(InMemoryDatabaseContext.class)
@ComponentScan(basePackageClasses = {JourneyServiceImpl.class, JourneyFacadeImpl.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    public class DozerCustomConfig extends BeanMappingBuilder {

        @Override
        protected void configure() {
            mapping(Employee.class, EmployeeDTO.class);
            mapping(Inspection.class, InspectionDTO.class);
            mapping(InspectionInterval.class, InspectionIntervalDTO.class);
            mapping(Journey.class, JourneyDTO.class);
            mapping(VehicleCategory.class, VehicleCategoryDTO.class);
            //mapping(Vehicle.class, VehicleDTO.class);
            mapping(Vehicle.class, VehicleDTO.class)
                .exclude("productionYear");
        }
    }
}