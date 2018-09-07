package com.cxgc.database.mapper;

import com.cxgc.database.model.RailInformation;

import java.util.List;

public interface RIMapper {
    public int add(RailInformation railInformation);

    public List<RailInformation> findByRailId(int railId);
}
