package com.example.demo;

import java.util.List;

import lombok.Getter;

@Getter
public class Response<T> {

    private List<T> res;

    public Response(List<T> res) {
        this.res = res;
    }
}
