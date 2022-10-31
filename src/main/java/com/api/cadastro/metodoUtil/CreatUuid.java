package com.api.cadastro.metodoUtil;

import java.util.UUID;

import static java.util.UUID.fromString;

public class CreatUuid {
    public String creatUuid(){
       UUID uuid = fromString(UUID.randomUUID().toString());
       return String.valueOf(uuid);
    }
}
