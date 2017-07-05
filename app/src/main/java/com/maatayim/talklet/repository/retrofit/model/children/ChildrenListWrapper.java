package com.maatayim.talklet.repository.retrofit.model.children;

import java.util.List;

/**
 * Created by Sophie on 5/28/2017
 */

public class ChildrenListWrapper {

    private List<ChildModel> children;

    public ChildrenListWrapper(List<ChildModel> children){

        this.children = children;
    }


    public List<ChildModel> getChildren() {
        return children;
    }

    public void setChildren(List<ChildModel> children) {
        this.children = children;
    }
}
