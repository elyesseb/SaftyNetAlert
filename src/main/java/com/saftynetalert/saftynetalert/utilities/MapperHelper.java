package com.saftynetalert.saftynetalert.utilities;

import org.modelmapper.ModelMapper;

public class MapperHelper<T> {
    private ModelMapper mapper = new ModelMapper();

    public T Mapping(Object object, Class<T> classType)
    {
        return mapper.map(object, classType);
    }
}
