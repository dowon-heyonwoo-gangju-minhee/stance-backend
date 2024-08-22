package com.stance.infra.tools;

import com.stance.domain.tools.Tools;

import java.util.List;

public class ToolsMapper {
    public static ToolsEntity toEntity(Tools tools) {
        return new ToolsEntity(tools.stackName());
    }
    public static List<ToolsEntity> toEntities(List<Tools> tools) {
        return Tools.toEntity(tools);
    }

    public static Tools toDomain(ToolsEntity toolsEntity) {
        return new Tools(toolsEntity.getName());
    }
}
