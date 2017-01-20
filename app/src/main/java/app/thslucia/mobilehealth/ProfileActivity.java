package app.thslucia.mobilehealth;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;

import app.thslucia.mobilehealth.model.Card;
import app.thslucia.mobilehealth.model.Patient;
import app.thslucia.mobilehealth.viewAdapter.CardViewAdapter;
import app.thslucia.mobilehealth.viewAdapter.PatientViewAdapter;

public class ProfileActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        //Default action
        getPatientsList();

        initNavigationDrawer();
    }

    public void initNavigationDrawer() {

        final NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                    // Handle navigation view item clicks here.
                    int id = menuItem.getItemId();

                    if (id == R.id.nav_todays_schedule) {
                        getScheduleList();
                    } else if (id == R.id.nav_patients) {
                        getPatientsList();
                    } else if (id == R.id.nav_logout) {
                        finish();
                    }

                    highlightItem(menuItem);
                    return true;
            }

            public void highlightItem(MenuItem newChecked) {
                    int size = navigationView.getMenu().size();
                    for (int i = 0; i < size; i++) {
                        navigationView.getMenu().getItem(i).setChecked(false);
                    }
                    newChecked.setChecked(true);
                    closeDrawer();
            }

            public void closeDrawer() {
                drawer.closeDrawer(GravityCompat.START);
            }

            public void openDrawer() {
                drawer.openDrawer(GravityCompat.START);
            }

        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    public void getPatientsList() {
        List<Patient> data = fillWithPatientData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.container_body);
        PatientViewAdapter adapter = new PatientViewAdapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void getScheduleList(){
        List<Card> data = fillWithData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.container_body);
        CardViewAdapter adapter = new CardViewAdapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //// TODO BORRAR TODO ESTO
    public List<Card> fillWithData() {
        List<Card> data = new ArrayList<>();
        data.add(new Card("Batman vs Superman", "Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", R.drawable.ic_menu_gallery));
        data.add(new Card("X-Men: Apocalypse", "X-Men: Apocalypse is an upcoming American superhero film based on the X-Men characters that appear in Marvel Comics ", R.drawable.ic_menu_gallery));
        data.add(new Card("Captain America: Civil War", "A feud between Captain America and Iron Man leaves the Avengers in turmoil.  ", R.drawable.ic_menu_gallery));
        data.add(new Card("Kung Fu Panda 3", "After reuniting with his long-lost father, Po  must train a village of pandas", R.drawable.ic_menu_gallery));
        data.add(new Card("Warcraft", "Fleeing their dying home to colonize another, fearsome orc warriors invade the peaceful realm of Azeroth. ", R.drawable.ic_menu_gallery));
        data.add(new Card("Alice in Wonderland", "Alice in Wonderland: Through the Looking Glass ", R.drawable.ic_menu_gallery));
        return data;
    }

    public List<Patient> fillWithPatientData() {
        List<Patient> data = new ArrayList<>();
        data.add(new Patient("Patricia", "Ximena", "Anchorena", "Vasquez", "Principal", "Canelones 2011 apto 53\nEntre Pablo de Maria y Blanes", "Principal", "099 809 789", "active"));
        data.add(new Patient("Silvia", "Cristina", "Fiandra", "Rocha", "Materna", "Carlos Gardel 198", "Principal", "2400 20 21", "inactive"));
        data.add(new Patient("Lucia", "Ibeth", "Figueroa", "Tasca", "Suegros", "Defensa 3090 esquina Goes", "Principal", "094 567 890", "active"));
        data.add(new Patient("Juan", "", "Laguna", "Ferreira", "Amigos", "San Juan Pastor 4530 apto 101\nEntre San Jose y Santa Marta", "Principal", "091 209 333", "active"));
        data.add(new Patient("Diego", "", "Martinez", "de la Escada", "Abuelos", "Avelino Miranda 2673 apto 003\nEntre Avenida Italia y Bahia Blanca", "Principal", "2900 890 789", "active"));
        data.add(new Patient("Andrés", "Marcelo", "Om", "Palacios", "Principal", "18 de Diciembre 1373 apto 308, Torre 1", "Principal", "093 456 900", "active"));
        return data;
    }
}
