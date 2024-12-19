package com.mergbw.core.bean;

import java.util.ArrayList;
import java.util.List;

public class SceneGroupData {
    private int groupNameRes;
    private int id;
    private List<SceneData> sceneDataList = new ArrayList();
    private int type;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getGroupNameRes() {
        return this.groupNameRes;
    }

    public void setGroupNameRes(int i) {
        this.groupNameRes = i;
    }

    public List<SceneData> getSceneDataList() {
        return this.sceneDataList;
    }

    public void setSceneDataList(List<SceneData> list) {
        this.sceneDataList = list;
    }
}
