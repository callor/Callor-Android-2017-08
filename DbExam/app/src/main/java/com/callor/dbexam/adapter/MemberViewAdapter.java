package com.callor.dbexam.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.callor.dbexam.R;
import com.callor.dbexam.db.MemberVO;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by callor on 2017-09-11.
 */
public class MemberViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<MemberVO> memberVO ;


    public MemberViewAdapter(List<MemberVO> memberVO) {
        this.memberVO = memberVO;
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // item을 표시할 view import
        View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.member_item,parent,false);

        MemberViewHolder viewHolder = new MemberViewHolder(v);
        return viewHolder;

    }

    // 실제 데이터를 연결
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MemberViewHolder mHolder = (MemberViewHolder)holder ;
        mHolder.itemUserId.setText(memberVO.get(position).getUserId());
        mHolder.itemUserEmail.setText(memberVO.get(position).getUserEmail());
        mHolder.itemUserPhone.setText(memberVO.get(position).getUserPhone());
    }

    @Override
    public int getItemCount() {
        return memberVO.size();
    }

    // layout/member_item.xml 파일을 가져와서,
    // list의 item을 표시하도록 연결하는 부분
    public class MemberViewHolder extends RecyclerView.ViewHolder {

        TextView itemUserId ;
        TextView itemUserEmail ;
        TextView itemUserPhone ;

        public MemberViewHolder(View itemView) {
            super(itemView);
            itemUserId = (TextView)itemView.findViewById(R.id.list_item_user_id);
            itemUserEmail = (TextView)itemView.findViewById(R.id.list_item_user_email);
            itemUserPhone = (TextView)itemView.findViewById(R.id.list_item_user_phone);
        }
    }
}
