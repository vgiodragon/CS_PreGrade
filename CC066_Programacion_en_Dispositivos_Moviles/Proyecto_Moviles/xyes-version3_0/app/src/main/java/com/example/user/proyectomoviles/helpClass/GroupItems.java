package com.example.user.proyectomoviles.helpClass;

import java.util.ArrayList;
import java.util.List;

public class GroupItems {
    public String string;

    public final List<String> children = new ArrayList<String>();

    public GroupItems(String string) {
        this.string = string;
    }
}
