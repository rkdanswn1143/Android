package com.example.teamproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListView list;
    String[] titles = {
            "The Wizard of Oz (1939)",
            "Citizen Kane (1941)",
            "All Aboute Eve (1950)",
            "The Third Man (1949)",
            "A Hard Day's Night (1964)",
            "Modern Times (1936)",
            "Metropolis (1927)",
            "Metropolis (1927)",
            "Metropolis (1927)",
            "Metropolis (1927)"
    };

    Integer [] images = {
            R.drawable.movie1,
            R.drawable.movie2,
            R.drawable.movie3,
            R.drawable.movie4,
            R.drawable.movie5,
            R.drawable.movie6,
            R.drawable.movie7,
            R.drawable.movie7,
            R.drawable.movie7,
            R.drawable.movie7
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        CustomList adapter = new CustomList(MainActivity.this);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),titles[+position],
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
    public class CustomList extends ArrayAdapter<String>{
    private final Activity context;
    public CustomList(Activity context){
        super(context,R.layout.listitem,titles);
        this.context = context;
    }

    @Override
        public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowVIew = inflater.inflate(R.layout.listitem,null,true);
        ImageView imageView = (ImageView) rowVIew.findViewById(R.id.image);
        TextView title = (TextView) rowVIew.findViewById(R.id.title);
        TextView rating = (TextView) rowVIew.findViewById(R.id.rating);
        TextView genre = (TextView) rowVIew.findViewById(R.id.genre);
        TextView year = (TextView) rowVIew.findViewById(R.id.year);

        title.setText(titles[position]);
        imageView.setImageResource(images[position]);
        rating.setText("9.0"+position);
        genre.setText("Drama");
        year.setText(1930+position+"");
        return rowVIew;

    }

    }
}
