package com.example.user.proyectomoviles.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by jbot on 23/02/16.
 */
public class MiFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] {  "Pagina Inicio", "Informacion","Notas"};
    private String ID_Alumno;

    public MiFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public MiFragmentPagerAdapter(FragmentManager fm,String cadena) {
        super(fm);
        this.ID_Alumno = cadena;
    }
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        switch(position) {
            case 0:
                f = HomeFragment.newInstance();
                break;
            case 1:
                f = InfoFragment.newInstance(ID_Alumno);
                break;
            case 2:
                f = GradesFragment.newInstance(ID_Alumno);
                break;
        }
        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }


}
