package com.example.testmovie;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView posterImageView;
    private TextView titleTextView, originalTitleTextView, overviewTextView, releaseDateTextView;
    private EditText etReview;
    private Button btnSubmitReview;
    private LinearLayout reviewContainer;
    private String movieId; // 영화 ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // UI 요소 초기화
        posterImageView = findViewById(R.id.posterImageView);
        titleTextView = findViewById(R.id.titleTextView);
        originalTitleTextView = findViewById(R.id.originalTitleTextView);
        overviewTextView = findViewById(R.id.overviewTextView);
        releaseDateTextView = findViewById(R.id.releaseDateTextView);
        etReview = findViewById(R.id.etReview);
        btnSubmitReview = findViewById(R.id.btnSubmitReview);
        reviewContainer = findViewById(R.id.reviewContainer);

        // Intent에서 영화 정보 가져오기
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String original_title = intent.getStringExtra("original_title");
        String poster_path = intent.getStringExtra("poster_path");
        String overview = intent.getStringExtra("overview");
        String release_date = intent.getStringExtra("release_date");
        movieId = intent.getStringExtra("movie_id"); // 영화 ID

        // UI에 영화 정보 설정
        titleTextView.setText(title);
        originalTitleTextView.setText(original_title);
        overviewTextView.setText(overview);
        releaseDateTextView.setText(release_date);

        // 포스터 이미지를 Glide 라이브러리를 사용하여 로드
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + poster_path)
                .thumbnail(0.1f)
                .centerCrop()
                .into(posterImageView);

        // 리뷰 표시
        displayReviews();

        // 리뷰 제출 버튼 클릭 리스너 설정
        btnSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String review = etReview.getText().toString();
                if (!review.isEmpty()) {
                    // 사용자 정보 가져오기
                    SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
                    String username = preferences.getString("username", "");
                    String name = preferences.getString("name", "");

                    // 데이터베이스에 리뷰 추가
                    DatabaseHelper dbHelper = new DatabaseHelper(DetailActivity.this);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("movie_id", movieId);
                    values.put("review", review);
                    values.put("username", username); // 사용자 아이디 저장
                    values.put("name", name); // 사용자 이름 저장
                    values.put("likes", 0); // 추천 초기값
                    values.put("dislikes", 0); // 비추천 초기값
                    db.insert("reviews", null, values);
                    db.close();
                    etReview.setText("");
                    displayReviews();
                } else {
                    Toast.makeText(DetailActivity.this, "리뷰를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // 리뷰를 표시하는 메서드
    private void displayReviews() {
        // 기존 리뷰 뷰들을 제거하여 중복 표시 방지
        reviewContainer.removeAllViews();

        // 데이터베이스에 접근하여 리뷰를 읽어옴
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, review, username, name, likes, dislikes FROM reviews WHERE movie_id=?", new String[]{movieId});

        // 리뷰가 존재하는 경우
        if (cursor.moveToFirst()) {
            do {
                // 리뷰 데이터 추출
                int reviewId = cursor.getInt(0);
                String review = cursor.getString(1);
                String username = cursor.getString(2);
                String name = cursor.getString(3);
                int likes = cursor.getInt(4);
                int dislikes = cursor.getInt(5);

                // 아이디의 뒷자리 두 자리를 지움
                String maskedUsername = maskUsername(username);

                // 리뷰 아이템 뷰를 생성하고 설정
                View reviewView = getLayoutInflater().inflate(R.layout.review_item, null);
                TextView reviewTextView = reviewView.findViewById(R.id.reviewTextView);
                TextView userInfoTextView = reviewView.findViewById(R.id.userInfoTextView);
                TextView likesTextView = reviewView.findViewById(R.id.likesTextView);
                TextView dislikesTextView = reviewView.findViewById(R.id.dislikesTextView);
                Button likeButton = reviewView.findViewById(R.id.likeButton);
                Button dislikeButton = reviewView.findViewById(R.id.dislikeButton);

                // 사용자 정보와 리뷰 내용을 설정
                reviewTextView.setText(review);
                userInfoTextView.setText("사용자: " + name + " (" + maskedUsername + ")");
                likesTextView.setText(String.valueOf(likes));
                dislikesTextView.setText(String.valueOf(dislikes));

                // 추천 버튼 클릭 리스너 설정
                likeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateLikes(reviewId, true);
                    }
                });

                // 비추천 버튼 클릭 리스너 설정
                dislikeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateLikes(reviewId, false);
                    }
                });

                // 리뷰 컨테이너에 추가
                reviewContainer.addView(reviewView);
            } while (cursor.moveToNext()); // 다음 리뷰로 이동
        }
        cursor.close(); // 커서 닫기
        db.close(); // 데이터베이스 닫기
    }

    // 아이디의 뒷자리 두 자리를 지우는 메서드
    private String maskUsername(String username) {
        if (username.length() > 2) {
            return username.substring(0, username.length() - 2) + "**";
        } else {
            return username;
        }
    }

    // 추천/비추천 업데이트 메서드
    private void updateLikes(int reviewId, boolean isLike) {
        // 데이터베이스에 접근하여 추천/비추천 수 업데이트
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (isLike) {
            db.execSQL("UPDATE reviews SET likes = likes + 1 WHERE id = ?", new Object[]{reviewId});
        } else {
            db.execSQL("UPDATE reviews SET dislikes = dislikes + 1 WHERE id = ?", new Object[]{reviewId});
        }
        db.close(); // 데이터베이스 닫기
        displayReviews(); // 리뷰 다시 표시
    }
}
