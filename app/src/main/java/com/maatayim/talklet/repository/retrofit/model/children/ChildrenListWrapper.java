package com.maatayim.talklet.repository.retrofit.model.children;

import java.util.List;

/**
 * Created by Sophie on 5/28/2017.
 */

public class ChildrenListWrapper {

    private List<Child> children;

    public ChildrenListWrapper(List<Child> children){

        this.children = children;
    }


    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }
}
