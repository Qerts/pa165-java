package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.InspectionIntervalDao;
import cz.fi.muni.pa165.entity.Inspection;
import cz.fi.muni.pa165.entity.InspectionInterval;
import org.dozer.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static java.util.Arrays.asList;

/**
 * @author Richard Trebichavský
 */
public class InspectionIntervalServiceImplTest {

    @Mock
    private InspectionIntervalDao inspectionIntervalDao;

    @Mock
    private DateTimeService dateTimeService;

    @Inject
    @InjectMocks
    private InspectionIntervalServiceImpl uut;

    @BeforeClass
    public void setUp() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        when(dateTimeService.getCurrentDate()).thenReturn(new GregorianCalendar(2016, 3, 1).getTime());
    }

    @Test
    public void testFindAllWithPlannedInspection() {
        // Arrange

        // InspectionInterval 1 - needs inspection (needs to be performed on the 5th day from today)
        Inspection inspection11 = mock(Inspection.class);
        // Inspection needs to be
        when(inspection11.getPerformedAt()).thenReturn((new GregorianCalendar(2016, 2, 6).getTime()));

        InspectionInterval interval1 = mock(InspectionInterval.class);
        when(interval1.getDays()).thenReturn(30);
        when(interval1.hasInspections()).thenReturn(true);
        when(interval1.getNewestInspection()).thenReturn(inspection11);

        // InspectionInterval 2 - do not need inspection (needs to be performed on the 6th day from today)
        Inspection inspection21 = mock(Inspection.class);
        when(interval1.hasInspections()).thenReturn(true);
        when(inspection21.getPerformedAt()).thenReturn((new GregorianCalendar(2016, 2, 7).getTime()));


        InspectionInterval interval2 = mock(InspectionInterval.class);
        when(interval2.getDays()).thenReturn(30);
        when(interval2.hasInspections()).thenReturn(true);
        when(interval2.getNewestInspection()).thenReturn(inspection21);

        // InspectionInterval 3 - needs inspection (was never inspected)
        InspectionInterval interval3 = mock(InspectionInterval.class);
        when(interval3.getDays()).thenReturn(30);
        when(interval3.hasInspections()).thenReturn(false);

        when(inspectionIntervalDao.findAll()).thenReturn(asList(interval1, interval2, interval3));

        // Act
        Collection<InspectionInterval> result = uut.findAllWithPlannedInspection(5);

        // Assert
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.toArray()[0], interval1);
        Assert.assertEquals(result.toArray()[1], interval3);
    }
}
