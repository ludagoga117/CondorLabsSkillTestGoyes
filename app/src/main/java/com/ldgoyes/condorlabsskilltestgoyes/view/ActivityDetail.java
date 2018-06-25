package com.ldgoyes.condorlabsskilltestgoyes.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ldgoyes.condorlabsskilltestgoyes.R;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.DBConstants;
import com.ldgoyes.condorlabsskilltestgoyes.interactor.database.holders.DetailHolder;
import com.ldgoyes.condorlabsskilltestgoyes.interfaces.InterfaceDetailPresenterView;
import com.ldgoyes.condorlabsskilltestgoyes.presenter.PresenterDetail;

public class ActivityDetail extends AppCompatActivity implements InterfaceDetailPresenterView {

    private PresenterDetail presenterDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        String movieId = getIntent().getExtras().getString(DBConstants.DataSummary.MOVIE_ID);
        String movieName = getIntent().getExtras().getString(DBConstants.DataSummary.MOVIE_NAME);

        presenterDetail = PresenterDetail.instanceOf(
                ActivityDetail.this,
                this,
                movieId,
                movieName
        );
        presenterDetail.start();
    }

    @Override
    public void setUI(final DetailHolder extractedData, String movieName) {
        TextView textViewTitle = (TextView) findViewById(R.id.activitydetail_textview_title );
        ImageView imageViewFavorite = (ImageView) findViewById(R.id.activitydetail_imageview_favorite);
        LinearLayout linearLayoutFavorite = (LinearLayout) findViewById(R.id.activitydetail_linearlayout_traileryoutube);
        TextView textViewOverview = (TextView) findViewById(R.id.activitydetail_textview_overviewcontent);
        TextView textViewReleaseDate = (TextView) findViewById(R.id.activitydetail_textview_releasedatecontent);
        TextView textViewBudget = (TextView) findViewById(R.id.activitydetail_textview_budget);

        if( extractedData.isFavorite ){
            imageViewFavorite.setImageResource( R.drawable.ic_favorite_black_24dp );
        }else{
            imageViewFavorite.setImageResource( R.drawable.ic_favorite_border_black_24dp );
        }
        imageViewFavorite.setColorFilter( ContextCompat.getColor( ActivityDetail.this, R.color.red) );
        imageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterDetail.invertFavoriteValue();
            }
        });

        if( extractedData.trailerLink == null ){
            linearLayoutFavorite.setVisibility( View.GONE );
        }else{
            linearLayoutFavorite.setVisibility( View.VISIBLE );
            linearLayoutFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent youtubeIntent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("vnd.youtube:" + extractedData.trailerLink
                            )
                    );
                    Intent youtubeWebIntent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v=" + extractedData.trailerLink
                            )
                    );
                    try {
                        ActivityDetail.this.startActivity(youtubeIntent);
                    } catch (ActivityNotFoundException ex) {
                        ActivityDetail.this.startActivity(youtubeWebIntent);
                    }
                }
            });
        }

        textViewTitle.setText( movieName );
        textViewOverview.setText( extractedData.movieOverview );
        textViewReleaseDate.setText( extractedData.releaseDate );
        textViewBudget.setText( extractedData.budget );
    }
}
