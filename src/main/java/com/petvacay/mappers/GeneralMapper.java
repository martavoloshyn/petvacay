package com.petvacay.mappers;

public interface GeneralMapper<M, D> {

    D convertToDto(M model)  ;

    M convertToModel(D dto) ;
}
