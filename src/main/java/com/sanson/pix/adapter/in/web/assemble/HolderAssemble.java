package com.sanson.pix.adapter.in.web.assemble;

import com.sanson.pix.domain.managerPix.Holder;
import com.sanson.pix.domain.managerPix.HolderType;

public class HolderAssemble {

    public static Holder assemble(String holderName, String holderLastName, HolderType holderType){
        return new Holder(holderName, holderLastName, holderType);
    }
}
