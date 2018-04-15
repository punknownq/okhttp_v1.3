package com.wang.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jpeng.jptabbar.JPTabBar;
import com.wang.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jpeng on 16-11-14.
 */
public class Tab1Pager extends Fragment implements View.OnClickListener, TextWatcher {


    private JPTabBar mTabBar;
    private  RecyclerView mRecyclerView;
    private Button add;
    private Button delete;
    private List<String> mDatas;
    private SimpleAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.tab1, null);
        init(layout);
        return layout;
    }

    private void init(View layout) {
        mTabBar = ((MainActivity)getActivity()).getTabbar();
        add=layout.findViewById(R.id.id_add);
        delete=layout.findViewById(R.id.id_delete);
        mRecyclerView =layout.findViewById(R.id.id_recyclerView);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.addData(1);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.deleteData(1);
            }
        });

        initDatas();
        //  initViews();
        mAdapter =new SimpleAdapter(getActivity(),mDatas);
        mRecyclerView.setAdapter(mAdapter);
        //设置RecycleView的布局管理
       // LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickLisener(new SimpleAdapter.OnItemClickLisener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),"click:"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(),"long click:"+position,Toast.LENGTH_SHORT).show();
            }
        });
        //设置Recycleview的Irem间分割线
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }


    @Override
    public void onClick(View v) {


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if(s!=null&&s.toString().equals("0")){
            mTabBar.showBadge(0, ""+0,true);
            mTabBar.hideBadge(0);
            return;
        }
        if (s.toString().equals("")) {
            mTabBar.showBadge(0, ""+0,true);
            return;
        }
        int count = Integer.parseInt(s.toString());
        if(mTabBar!=null)
            mTabBar.showBadge(0, count+"",true);
    }

    public void clearCount() {
        //当徽章拖拽爆炸后,一旦View被销毁,不判断就会空指针异常
//        if(mNumberEt!=null)
//            mNumberEt.setText("0");
    }
    public void initDatas() {
        mDatas=new ArrayList<>();
        for(int i='A';i<='z';i++){
            mDatas.add(""+(char)i);
        }

    }


}

