package com.example.user.proyectomoviles.Model;

import java.util.ArrayList;
import java.util.List;

public class GrupoDeItems {
    public String string;

    public final List<String> children = new ArrayList<String>();

    public GrupoDeItems(String string) {
        this.string = string;
    }
}
