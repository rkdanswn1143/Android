package com.example.testmovie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private ArrayList<Movie> movieList;
    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Welcome text view
        welcomeTextView = findViewById(R.id.welcomeTextView);
        String userName = getIntent().getStringExtra("userName");
        if (userName != null) {
            welcomeTextView.setText(userName + "님 반갑습니다..!");
            welcomeTextView.setTextColor(ContextCompat.getColor(this, R.color.menuTextColor));
        }

        recyclerView = findViewById(R.id.recycler_view);
        movieList = new ArrayList<>();

        adapter = new MyRecyclerViewAdapter(MainActivity.this, movieList);
        recyclerView.setAdapter(adapter);

        // Asynctask - OKHttp
        MyAsyncTask mAsyncTask = new MyAsyncTask();
        mAsyncTask.execute("https://api.themoviedb.org/3/movie/upcoming?api_key=a0ca9c71f23c2d068dfe30b5b3ff41f1&language=ko-KR&page=1");

        // LayoutManager
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("영화제목을 입력하세요.");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                // 검색어 제출 시 실행
                String searchUrl = "https://api.themoviedb.org/3/search/movie?api_key=a0ca9c71f23c2d068dfe30b5b3ff41f1&query=" + query + "&language=ko-KR&page=1";
                new MyAsyncTask().execute(searchUrl);
                Toast.makeText(MainActivity.this, query + "에 대한 영화를 검색합니다.", Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // 검색어 변경 시 실행 (필요 시 구현)
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        } else if (id == R.id.action_popular) {
            new MyAsyncTask().execute("https://api.themoviedb.org/3/movie/popular?api_key=a0ca9c71f23c2d068dfe30b5b3ff41f1&language=ko-KR&page=1");
            Toast.makeText(this, "인기 영화를 불러옵니다.", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_now_playing) {
            new MyAsyncTask().execute("https://api.themoviedb.org/3/movie/now_playing?api_key=a0ca9c71f23c2d068dfe30b5b3ff41f1&language=ko-KR&page=1");
            Toast.makeText(this, "현재 상영 중인 영화를 불러옵니다.", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_upcoming) {
            new MyAsyncTask().execute("https://api.themoviedb.org/3/movie/upcoming?api_key=a0ca9c71f23c2d068dfe30b5b3ff41f1&language=ko-KR&page=1");
            Toast.makeText(this, "개봉 예정 영화를 불러옵니다.", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_top_rated) {
            new MyAsyncTask().execute("https://api.themoviedb.org/3/movie/top_rated?api_key=a0ca9c71f23c2d068dfe30b5b3ff41f1&language=ko-KR&page=1");
            Toast.makeText(this, "높은 평점의 영화를 불러옵니다.", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_genre_action) {
            new MyAsyncTask().execute("https://api.themoviedb.org/3/discover/movie?api_key=a0ca9c71f23c2d068dfe30b5b3ff41f1&language=ko-KR&with_genres=28&page=1");
            Toast.makeText(this, "액션 영화를 불러옵니다.", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_genre_romance) {
            new MyAsyncTask().execute("https://api.themoviedb.org/3/discover/movie?api_key=a0ca9c71f23c2d068dfe30b5b3ff41f1&language=ko-KR&with_genres=10749&page=1");
            Toast.makeText(this, "로맨스 영화를 불러옵니다.", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_genre_horror) {
            new MyAsyncTask().execute("https://api.themoviedb.org/3/discover/movie?api_key=a0ca9c71f23c2d068dfe30b5b3ff41f1&language=ko-KR&with_genres=27&page=1");
            Toast.makeText(this, "공포 영화를 불러옵니다.", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.action_logout) {
            // 로그아웃 처리
            SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear(); // 모든 세션 데이터 삭제
            editor.apply();

            // 로그인 화면으로 이동
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyAsyncTask extends AsyncTask<String, Void, Movie[]> {
        @Override
        protected Movie[] doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(strings[0])
                    .build();
            try {
                Response response = client.newCall(request).execute();
                Gson gson = new GsonBuilder().create();
                JsonParser parser = new JsonParser();
                JsonElement rootObject = parser.parse(response.body().charStream())
                        .getAsJsonObject().get("results");
                return gson.fromJson(rootObject, Movie[].class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Movie[] result) {
            super.onPostExecute(result);
            if (result != null && result.length > 0) {
                movieList.clear();
                for (Movie p : result) {
                    movieList.add(p);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.RecyclerViewHolders> {

        private ArrayList<Movie> mMovieList;
        private LayoutInflater mInflate;
        private Context mContext;

        // Constructor
        public MyRecyclerViewAdapter(Context context, ArrayList<Movie> itemList) {
            this.mContext = context;
            this.mInflate = LayoutInflater.from(context);
            this.mMovieList = itemList;
        }

        @NonNull
        @Override
        public RecyclerViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflate.inflate(R.layout.list_item, parent, false);
            return new RecyclerViewHolders(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolders holder, final int position) {
            //포스터만 출력하자.
            String url = "https://image.tmdb.org/t/p/w500" + mMovieList.get(position).getPoster_path();
            Glide.with(mContext)
                    .load(url)
                    .centerCrop()
                    .into(holder.imageView);

            //각 아이템 클릭 이벤트
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("title", mMovieList.get(position).getTitle());
                    intent.putExtra("original_title", mMovieList.get(position).getOriginal_title());
                    intent.putExtra("poster_path", mMovieList.get(position).getPoster_path());
                    intent.putExtra("overview", mMovieList.get(position).getOverview());
                    intent.putExtra("release_date", mMovieList.get(position).getRelease_date());
                    intent.putExtra("movie_id", mMovieList.get(position).getId()); // 영화 ID 추가
                    mContext.startActivity(intent);
                    Log.d("Adapter", "Clicked: " + position);
                }
            });
        }


        @Override
        public int getItemCount() {
            return this.mMovieList.size();
        }

        // ViewHolder class
        public class RecyclerViewHolders extends RecyclerView.ViewHolder {
            public ImageView imageView;

            public RecyclerViewHolders(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
    }
}
