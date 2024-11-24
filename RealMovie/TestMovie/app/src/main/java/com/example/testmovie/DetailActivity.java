package com.example.testmovie;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView posterImageView;
    private TextView titleTextView, originalTitleTextView, overviewTextView, releaseDateTextView;
    private EditText etReview;
    private Button btnSubmitReview;
    private LinearLayout reviewContainer;
    private String movieId; // 영화 ID
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        posterImageView = findViewById(R.id.posterImageView);
        titleTextView = findViewById(R.id.titleTextView);
        originalTitleTextView = findViewById(R.id.originalTitleTextView);
        overviewTextView = findViewById(R.id.overviewTextView);
        releaseDateTextView = findViewById(R.id.releaseDateTextView);
        etReview = findViewById(R.id.etReview);
        btnSubmitReview = findViewById(R.id.btnSubmitReview);
        reviewContainer = findViewById(R.id.reviewContainer);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String original_title = intent.getStringExtra("original_title");
        String poster_path = intent.getStringExtra("poster_path");
        String overview = intent.getStringExtra("overview");
        String release_date = intent.getStringExtra("release_date");
        movieId = intent.getStringExtra("movie_id"); // 영화 ID

        titleTextView.setText(title);
        originalTitleTextView.setText(original_title);
        overviewTextView.setText(overview);
        releaseDateTextView.setText(release_date);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + poster_path)
                .thumbnail(0.1f)
                .centerCrop()
                .into(posterImageView);

//        btnSubmitReview.setOnClickListener(view -> {
//            String review = etReview.getText().toString();
//            if (!review.isEmpty()) {
//                DatabaseHelper dbHelper = new DatabaseHelper(DetailActivity.this);
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put(DatabaseHelper.COLUMN_MOVIE_ID, movieId); // 영화 ID
//                values.put(DatabaseHelper.COLUMN_USER_ID_FK, userId);
//                values.put(DatabaseHelper.COLUMN_REVIEW, review);
//                long newRowId = db.insert(DatabaseHelper.TABLE_REVIEWS, null, values);
//                if (newRowId != -1) {
//                    Toast.makeText(DetailActivity.this, "리뷰가 성공적으로 추가되었습니다.", Toast.LENGTH_SHORT).show();
//                    etReview.setText("");
//                    displayReviews();
//                } else {
//                    Toast.makeText(DetailActivity.this, "리뷰 추가에 실패했습니다.", Toast.LENGTH_SHORT).show();
//                }
//                db.close();
//            } else {
//                Toast.makeText(DetailActivity.this, "리뷰를 입력해주세요.", Toast.LENGTH_SHORT).show();
//            }
//        });

        displayReviews();
    }

    private void displayReviews() {
        if (movieId == null) {
            return;
        }

        reviewContainer.removeAllViews();

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT review FROM reviews WHERE movie_id=?", new String[]{movieId});

        if (cursor.moveToFirst()) {
            do {
                String review = cursor.getString(0);
                TextView reviewTextView = new TextView(this);
                reviewTextView.setText(review);
                reviewTextView.setTextSize(16f);
                reviewTextView.setPadding(0, 4, 0, 4);
                reviewContainer.addView(reviewTextView);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
    }
}
