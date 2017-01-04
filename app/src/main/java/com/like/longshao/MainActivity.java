package com.like.longshao;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.like.longshao.activity.basetheme.CdBaseThemeActivity;
import com.like.longshaolib.interfaces.SubscriberOnNextListener;

public class MainActivity extends CdBaseThemeActivity implements View.OnClickListener{
    private ListView listView;
    private Button button;
    private TextView textview;
    private ImageView imageView;
  //  private Subscriber<MovieEntity<List<Subject>>> subscriber;
    private SubscriberOnNextListener getTopMovie;
    private View theView;

    @Override
    protected void bindView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
//        listView = (ListView) findViewById(R.id.listview);
//        button = (Button) findViewById(R.id.button);
//        textview = (TextView) findViewById(R.id.textview);
//        imageView= (ImageView) findViewById(R.id.image);
//        theView=findViewById(R.id.theView);
    }


    protected void bindListener() {
        button.setOnClickListener(this);
    }

    @Override
    protected void initData() {
//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < 13; i++)
//            list.add("逗比" + i);
//        listView.setAdapter(new MyBaseAdapter(this, list));
//
////        getTopMovie=new SubscriberOnNextListener<List<Subject>>() {
////            @Override
////            public void onNext(List<Subject> subjects) {
////                textview.setText(subjects.get(0).getTitle());
////            }
////        };
//
//        ImageUitls.loadImage(this,"http://g.hiphotos.baidu.com/image/pic/item/6c224f4a20a446230761b9b79c22720e0df3d7bf.jpg",imageView);
    }

    @Override
    protected boolean isLoadView() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
//                int curr=v.getSystemUiVisibility();
//                int newcurr;
//                if ((curr&View.SYSTEM_UI_FLAG_LOW_PROFILE)==View.SYSTEM_UI_FLAG_LOW_PROFILE)
//                    newcurr=View.SYSTEM_UI_FLAG_VISIBLE;
//                else
//                    newcurr=View.SYSTEM_UI_FLAG_LOW_PROFILE;
//                v.setSystemUiVisibility(newcurr);

                if(theView.getAlpha()>0f){
                    theView.animate().alpha(0f).translationX(1000f);
                }else {
                    theView.setTranslationX(0f);
                    theView.animate().alpha(1f);
                }
//                getMovie();
                break;
        }
    }

    /**
     * 获取网络数据
     */
    private void getMovie() {
//        String baseUrl = "https://api.douban.com/v2/movie/";
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
//
//        CdBaseService service = retrofit.create(CdBaseService.class);
//        service.getTopMovice(0,1)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MovieEntity>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(MainActivity.this,"completeed",Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        textview.setText(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(MovieEntity movieEntity) {
//                        textview.setText(movieEntity.getTitle());
//                    }
//                });

//        subscriber=new Subscriber<MovieEntity<List<Subject>>>() {
//            @Override
//            public void onCompleted() {
//                Toast.makeText(MainActivity.this,"completeed",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                textview.setText(e.getMessage());
//            }
//
//            @Override
//            public void onNext(MovieEntity<List<Subject>> listMovieEntity) {
//                textview.setText(((List<Subject>)listMovieEntity.getSubjects()).get(0).getTitle());
//            }
//        };
     //   CdHttpManger.getInstance(CdBaseService.class).getTopMovie(new ProgressSubscriber<List<Subject>>(this,getTopMovie),0,1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // subscriber.unsubscribe();
    }

    private void back(){
        finish();
        overridePendingTransition(R.anim.activity_close_enter,R.anim.activity_close_exit);
    }
}
