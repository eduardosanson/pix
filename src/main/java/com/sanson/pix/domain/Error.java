package com.sanson.pix.domain;

public class Error {

    public static void required(String name) throws BusinessException {
        throw new BusinessException(name + " is required");
    }

    public static void invalid(String name) throws BusinessException {
        throw new BusinessException(name + " is invalid");
    }

    public static void notPermitted(String value) throws BusinessException {
        throw new BusinessException("is not permitted " + value);
    }

    public static void notFound(String name) throws BusinessException {
        throw new BusinessException(name + " not found");
    }
}
