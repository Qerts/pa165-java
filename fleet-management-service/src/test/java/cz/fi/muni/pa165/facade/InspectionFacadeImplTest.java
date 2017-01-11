package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.config.ServiceConfiguration;
import cz.fi.muni.pa165.dto.InspectionDTO;
import cz.fi.muni.pa165.dto.InspectionIntervalDTO;
import cz.fi.muni.pa165.entity.Inspection;
import cz.fi.muni.pa165.entity.InspectionInterval;
import cz.fi.muni.pa165.service.interfaces.BeanMappingService;
import cz.fi.muni.pa165.service.interfaces.InspectionIntervalService;
import cz.fi.muni.pa165.service.interfaces.InspectionService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Jozef Krcho
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class InspectionFacadeImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private InspectionIntervalService inspectionIntervalService;

    @Mock
    private InspectionService inspectionService;

    @InjectMocks
    private InspectionFacadeImpl inspectionFacade;

    private Inspection inspection;

    private InspectionDTO inspectionDTO;

    private InspectionInterval inspectionInterval;

    private InspectionIntervalDTO inspectionIntervalDTO;

    private Date date = new Date();
    private String name = "interval name";
    private int interval = 15;


    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        inspection = new Inspection(date);
        inspectionDTO = new InspectionDTO(date);
        inspectionInterval = new InspectionInterval(name, interval);
        inspectionIntervalDTO = new InspectionIntervalDTO(name, interval);


    }

    @Test
    public void testListAllInspectionIntervals() {
        when(beanMappingService.mapTo(Collections.singletonList(inspectionInterval), InspectionIntervalDTO.class))
                .thenReturn(Collections.singletonList(inspectionIntervalDTO));
        when(inspectionIntervalService.findAll()).thenReturn(Collections.singletonList(inspectionInterval));

        Assert.assertEquals(inspectionFacade.listAllInspectionIntervals().size(), 1);

        verify(inspectionIntervalService).findAll();
    }

    @Test
    public void testListPlannedInspectionIntervals() {
        int days = 5;
        when(beanMappingService.mapTo(Collections.singletonList(inspectionInterval), InspectionIntervalDTO.class))
                .thenReturn(Collections.singletonList(inspectionIntervalDTO));
        when(inspectionIntervalService.findAllWithPlannedInspection(days)).thenReturn(Collections.singletonList(inspectionInterval));


        Assert.assertEquals(inspectionFacade.listPlannedInspectionIntervals(days).size(), 1);

        verify(inspectionIntervalService).findAllWithPlannedInspection(days);
    }
}
