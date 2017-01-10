package cz.fi.muni.pa165.config;

import cz.fi.muni.pa165.InMemoryDatabaseContext;
import cz.fi.muni.pa165.facade.JourneyFacadeImpl;
import cz.fi.muni.pa165.security.SecurityConfig;
import cz.fi.muni.pa165.service.JourneyServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Schmidt
 */
@SuppressWarnings({"unchecked", "SpringFacetCodeInspection"})
@Configuration
@Import({InMemoryDatabaseContext.class, SecurityConfig.class})
@ComponentScan(basePackageClasses = {JourneyServiceImpl.class, JourneyFacadeImpl.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        List dozerMappingFiles = new ArrayList();
        dozerMappingFiles.add("file:///" + Paths.get("").toAbsolutePath().toString() + "/dozer.config.xml");
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.setMappingFiles(dozerMappingFiles);

        return dozer;
    }
}
