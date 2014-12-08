package com.example.rals.menpropuesto;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public abstract class MiActivityAbstracta extends Activity {

    private ListView listPaises;
    private ArrayList<String> paises;
    private ArrayAdapter<String> adapter;
    private Boolean menu;



    public abstract Boolean menuContextual();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        getActionBar().setHomeButtonEnabled(true);

        //Llenamos el array de paises
        obtenerPaises();

        //Establecemos eltipo de menu que implaemantará
        menu = menuContextual();

        //Instaciamos el adaptador
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, paises);

        //Asignamos la vista a la lista y luego le asignamos el adaptador
        listPaises = (ListView)findViewById(R.id.list_paises);
        listPaises.setAdapter(adapter);

        if (menu){
            registerForContextMenu(listPaises);
        }
        else{
            listPaises.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            listPaises.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                @Override
                public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                    MenuInflater menuInflater = getMenuInflater();
                    menuInflater.inflate(R.menu.menu_contextual, menu);

                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.menu_context_eliminar:

                            return true;
                        default:
                            return false;
                    }

                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            });
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_settings:

                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_context_eliminar:

                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void obtenerPaises(){

        paises = new ArrayList<>();
        paises.add("Vanezuela");
        paises.add("España");
        paises.add("Bélgica");
        paises.add("Alemania");
        paises.add("Reino Unido");
        paises.add("Portugal");
        paises.add("Austria");
        paises.add("Francia");
        paises.add("Italia");
        paises.add("Grecia");
        paises.add("Republica Checa");
        paises.add("Brazil");
        paises.add("EEUU");
        paises.add("Colombia");
        paises.add("Ecuador");
        paises.add("Mexico");
        paises.add("Chile");
        paises.add("Argentina");
        paises.add("Nicaragua");

    }
}
