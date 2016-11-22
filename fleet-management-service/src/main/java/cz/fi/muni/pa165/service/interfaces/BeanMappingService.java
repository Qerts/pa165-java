package cz.fi.muni.pa165.service.interfaces;

import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;


public interface BeanMappingService {

    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);

    Mapper getMapper();
}
