//package com.ak0411.filmfolio.mappers.impl;
//
//import com.ak0411.filmfolio.mappers.Mapper;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class GenericMapperImpl<E, D> implements Mapper<E, D> {
//
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    private Class<E> entityClass;
//
//    @Autowired
//    private Class<D> dtoClass;
//
//    public GenericMapperImpl(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }
//
//    @Override
//    public D mapTo(E entity) {
//        return modelMapper.map(entity, dtoClass);
//    }
//
//    @Override
//    public E mapFrom(D dto) {
//        return modelMapper.map(dto, entityClass);
//    }
//
//    @Override
//    public List<D> mapAll(Collection<E> entities) {
//        return entities.stream()
//                .map(this::mapTo)
//                .collect(Collectors.toList());
//    }
//}
//
