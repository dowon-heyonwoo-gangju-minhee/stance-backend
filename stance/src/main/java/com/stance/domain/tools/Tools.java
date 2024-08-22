package com.stance.domain.tools;

import com.stance.infra.tools.ToolsEntity;

import java.util.ArrayList;
import java.util.List;

public record Tools(String stackName){

    public static List<Tools> from(List<ToolsEntity> tools) {
        List<Tools> toolsList = new ArrayList<>();
        for(ToolsEntity tool : tools){
            toolsList.add(new Tools(tool.getName()));
        }
        return toolsList;
    }

    public static List<ToolsEntity> toEntity(List<Tools> tools) {
        List<ToolsEntity> toolsEntities = new ArrayList<>();
        for(Tools tool : tools){
            toolsEntities.add(new ToolsEntity(tool.stackName()));
        }
        return toolsEntities;
    }
}
