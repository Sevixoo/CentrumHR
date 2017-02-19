package com.centrumhr.data.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Seweryn on 06.02.2017.
 */
public abstract class Mapper<MODEL,DTO> {

    public abstract MODEL forward( DTO dto );

    public abstract DTO backward( MODEL model );

    public List<MODEL> forward( Collection<DTO> dtos ){
        if(dtos==null)return null;
        List<MODEL> list = new ArrayList<MODEL>();
        for (DTO dto : dtos) {
            list.add(forward(dto));
        }
        return list;
    }

    public List<DTO> backward( Collection<MODEL> models ){
        if(models==null)return null;
        List<DTO> list = new ArrayList<DTO>();
        for (MODEL model : models) {
            list.add(backward(model));
        }
        return list;
    }

}
